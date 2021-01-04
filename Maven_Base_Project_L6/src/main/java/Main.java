public class Main extends Thread{







    // Types of Repository
    // 1. Local Repository - ~/.m2/repository/
    // 2. Central Repository - https://repo.maven.apache.org/
    // 3. Remote Repository - Nexus, JFrog


    // 1. maven searches in local maven repo
    // 2. remote repo
    // 3. central repo


    // mvn package

    public static int add(int a, int b){
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
