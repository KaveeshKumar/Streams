
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class News { int newsId; String postedByUser; String commentByUser; String comment;

    public News(int newsId, String postedByUser, String commentByUser, String comment) {
        this.newsId = newsId;
        this.postedByUser = postedByUser;
        this.commentByUser = commentByUser;
        this.comment = comment;
    }

    public int getNewsId() {
        return newsId;
    }



    public String getCommentByUser() {
        return commentByUser;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", postedByUser='" + postedByUser + '\'' +
                ", commentByUser='" + commentByUser + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
public class AssignmentQ2 {
    public static int maxComments(List<News> news) {
        List<Integer> newsid = new ArrayList<>();
        news.forEach(p -> newsid.add(p.getNewsId()));
        return newsid.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(newsid, o)))).orElse(0);
    }
    public static int budgetCount (List < News > news) {
        int budget= (int) news.stream()
                .filter(p->p.getComment().contains("budget")).count();
        return budget;
    }
    public static String maxUsercomments(List<News> news) {
        List<String> newsC = new ArrayList<>();
        news.forEach(p -> newsC.add(p.getCommentByUser()));
        String maxCommented = newsC.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(newsC, o)))).orElse(null);
        return maxCommented;
    }

    public static Map<String,Integer> displaycommentsbyuser(List<News> news) {
        Map<String, Long> m;
        m=news.stream().collect(Collectors.groupingBy(News::getCommentByUser,Collectors.counting()));
        List < Map.Entry <String, Long> > list = new LinkedList<>(m.entrySet());
        list.sort((o1, o2) -> -(o1.getValue()).compareTo(o2.getValue()));
        HashMap<String, Integer> comCountByUser = new LinkedHashMap<>();
        for (Map.Entry<String, Long> aa : list) {
            comCountByUser.put(aa.getKey(), (int) (long)aa.getValue());
        }
        return comCountByUser;

    }
    public static void main(String[] args) {

        List<News> feb23 = new ArrayList<>();
        feb23.add(new News(1, "Ajith", "kumar", "comment"));
        feb23.add(new News(2, "Joseph", "Vijay", "comment"));
        feb23.add(new News(3, "Suriya", "Vijay", "budget"));
        feb23.add(new News(2, "karthi", "Siva", "budget "));
        feb23.add(new News(1, "vijay", "antony", "costly"));
        feb23.add(new News(3, "Vijay", "kumar", "costly"));

        System.out.println(maxComments(feb23));
        System.out.println(budgetCount(feb23));
        System.out.println(maxUsercomments(feb23));
        System.out.println(displaycommentsbyuser(feb23));

    }
}
