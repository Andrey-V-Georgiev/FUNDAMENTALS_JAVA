import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class _3_ {

    public static void main(String[] args) {

        class FollowerInfo {
            private int likes;
            private int comments;

            public FollowerInfo() {
                this.likes = 0;
                this.comments = 0;
            }

            public FollowerInfo(int likes, int comments) {
                this.likes = likes;
                this.comments = comments;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public void increaseLikes(int num) {
                this.likes = this.likes + num;
            }

            public void increaseComments(int num) {
                this.comments = this.comments + num;
            }

            public int sumLikesAndComments() {
                return this.comments + this.likes;
            }
        }

        Scanner scanner = new Scanner(System.in);
        Map<String, FollowerInfo> followers = new LinkedHashMap<>();

        String line = "";
        while (!(line = scanner.nextLine()).equals("Log out")) {

            String[] tokens = Arrays.stream(line.split(":")).map(String::trim).toArray(String[]::new);
            String command = tokens[0];
            String username = tokens[1];

            switch (command) {
                case "New follower":
                    if (!followers.containsKey(username)) {
                        followers.put(username, new FollowerInfo());
                    }
                    break;

                case "Like":
                    int likes = Integer.parseInt(tokens[2]);
                    if (!followers.containsKey(username)) {
                        followers.put(username, new FollowerInfo(likes, 0));
                    } else {
                        followers.get(username).increaseLikes(likes);
                    }
                    break;

                case "Comment":
                    if (!followers.containsKey(username)) {
                        followers.put(username, new FollowerInfo(0, 1));
                    } else {
                        followers.get(username).increaseComments(1);
                    }
                    break;

                case "Blocked":
                    if (!followers.containsKey(username)) {
                        System.out.printf("%s doesn't exist.\n", username);
                    } else {
                        followers.remove(username);
                    }
                    break;
            }
        }

        System.out.printf("%d followers\n", followers.size());
        followers.entrySet().stream()
                .sorted((f1, f2) -> {
                    int result = f2.getValue().sumLikesAndComments() - f1.getValue().sumLikesAndComments();
                    if (result == 0) {
                        result = f1.getKey().compareTo(f2.getKey());
                    }
                    return result;
                })
                .forEach(follower -> {
                    System.out.printf("%s: %d\n", follower.getKey(), follower.getValue().sumLikesAndComments());
                });
    }
}
