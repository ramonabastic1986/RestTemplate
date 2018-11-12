import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by fstancu on 11/9/2016.
 */

public class Main {

    public static void main(String[] args) {
        useRestTemplate();
        useHttpClient();

    }

    public static void useRestTemplate() {
        CommentWebServiceClient service = new UsingRestTemplate();

        // save
        Comment commentToBeSaved = new Comment("Ramona", "stancu.florin23@gmail.com", "test from java", new Date());
        Comment commentSaved = service.save(commentToBeSaved);
        System.out.println(commentSaved);

        String idP = Main.getParsedCommentId(commentSaved.get_links().toString());
        System.out.println("Created a comment with ID: " + idP);

        // findById
        Comment commentWithId = service.findById(Long.parseLong(idP));
        System.out.println("Founded comment is: " + commentWithId);

        // update
        commentWithId.setComment("test with rest template");
        service.update(commentWithId);

        Comment commentUpdate = service.findById(Long.parseLong(idP));
        System.out.println("Update is :" + commentUpdate);

        // findAll
        List<Comment> listOfComments = service.findAll();
        System.out.println("Got " + listOfComments.size() + " comments");
        for (Object o : listOfComments) {
            System.out.println(o);
        }

        // delete
        long idL = Long.parseLong(idP);
        System.out.println("Deleted comment with id " + idP);
        service.delete(idL);
    }

    public static void useHttpClient() {
        CommentWebServiceClient service = new UsingRestTemplate();

        // save with RestTemplate
        // save and update methods are implemented, but because of the field Date are not called.
        // The format in the serialize is not correct.
        // error: Can not construct instance of java.util.Date from String value
        Comment commentToBeSaved = new Comment("Ramona", "stancu.florin23@gmail.com", "test from java", new Date());
        Comment commentSaved = service.save(commentToBeSaved);
        System.out.println(commentSaved);

        String idP = Main.getParsedCommentId(commentSaved.get_links().toString());
        System.out.println("Created a comment wit ID: " + idP);

        // findAll
        HttpClientTemplate gj = new HttpClientTemplate();

        List<Comment> listOfComments = gj.findAll();
        System.out.println("Got " + listOfComments.size() + " comments");
        for (Object o : listOfComments) {
            System.out.println(o);
        }

        // findById
        Comment comm = gj.findById(Long.parseLong(idP));
        System.out.println("Founded id's comment is: " + comm);

        // delete
        long idL = Long.parseLong(idP);
        System.out.println("Deleted comment with id " + idP);
        gj.delete(idL);

    }

    public static String getParsedCommentId(String links) {
        String href = links.toString().split(" ")[0];
        String[] href2 = href.split("/");
        String href3 = href2[href2.length - 1].replace("},","");

        return href3;
    }
    
    private int compareVersions(String version1, String version2) {
    if (version1 == null || version2 == null) {
        throw new IllegalArgumentException("Versions can not be null")
    }

    def arr1 = version1.split("\\.")
    def arr2 = version2.split("\\.")
    while(vals1.size() != vals2.size()) {
		if(vals1.size() < vals2.size()) {
			vals1.add("0");
		} else {
			vals2.add("0");
		}
	}
    int i = 0

    while (i < arr1.length || i < arr2.length) {
        if (i < arr1.length && i < arr2.length) {
            if (Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])) {
                return -1
            } else if (Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])) {
                return 1
            }
        } else if (i < arr1.length) {
            if (Integer.parseInt(arr1[i]) != 0) {
                return 1
            }
        } else if (i < arr2.length) {
            if (Integer.parseInt(arr2[i]) != 0) {
                return -1
            }
        }

        i++
    }

    return 0
}
}

