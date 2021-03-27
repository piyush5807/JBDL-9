package com.example.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final String WALLET_CREATE_TOPIC = "wallet_create";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String REDIS_USER_KEY_PREFIX = "user::";

    public void createUser(UserRequest userRequest) throws JsonProcessingException {
        // create a user instance
        User user = User.builder()
                .age(userRequest.getAge())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .userId(userRequest.getUserId())
                .build();
        userRepository.save(user);

        // TODO: Insert the data in redis as well with some expiry

        Map map = objectMapper.convertValue(user, Map.class);
        redisTemplate.opsForHash().putAll(REDIS_USER_KEY_PREFIX + userRequest.getUserId(), map);

        // TODO: Publish a kafka event for creating a user's wallet

        JSONObject walletCreateRequest = new JSONObject();
        walletCreateRequest.put("userId", userRequest.getUserId());
        kafkaTemplate.send(WALLET_CREATE_TOPIC, user.getUserId(), objectMapper.writeValueAsString(walletCreateRequest));

    }

    public UserResponse getUserByUserId(String userId) {
        try {
            // TODO: Query from redis, if not found then query db

            Map<Object, Object> map = redisTemplate.opsForHash().entries(REDIS_USER_KEY_PREFIX + userId);

            User user = objectMapper.convertValue(map, User.class);

            if (map == null || map.size() == 0) {
                user =  userRepository.findByUserId(userId);
                if (user == null) {
                    return UserResponse.builder().user(null).status(404).email(null).build();
                }
                map = objectMapper.convertValue(user, Map.class);
                redisTemplate.opsForHash().putAll(REDIS_USER_KEY_PREFIX + userId, map);

            }
            return UserResponse.builder().user(user).email(user.getEmail()).status(200).build();
        } catch (Exception e) {
            return UserResponse.builder().user(null).email(null).status(500).build();
        }
    }
}
