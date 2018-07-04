package online.omnia.corrector;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lollipop on 23.10.2017.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int i = 0;
        List<PostBackEntity> postBackEntities;
        String tempLine;
        while (true) {
            postBackEntities = MySQLDaoImpl.getInstance().getPostbacks(i);
            if (postBackEntities.isEmpty()) break;
            for (PostBackEntity postBackEntity : postBackEntities) {
                tempLine = postBackEntity.getOfferName();
                if (tempLine != null) postBackEntity.setOfferName(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT1();
                if (tempLine != null) postBackEntity.setT1(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT2();
                if (tempLine != null) postBackEntity.setT2(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT3();
                if (tempLine != null) postBackEntity.setT3(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT4();
                if (tempLine != null) postBackEntity.setT4(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT5();
                if (tempLine != null) postBackEntity.setT5(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT6();
                if (tempLine != null) postBackEntity.setT6(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT7();
                if (tempLine != null) postBackEntity.setT7(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT8();
                if (tempLine != null) postBackEntity.setT8(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT9();
                if (tempLine != null) postBackEntity.setT9(URLEncoder.encode(tempLine, "UTF-8"));
                tempLine = postBackEntity.getT10();
                if (tempLine != null) postBackEntity.setT10(URLEncoder.encode(tempLine, "UTF-8"));
                MySQLDaoImpl.getInstance().update(postBackEntity);
            }
        }
        MySQLDaoImpl.getMasterDbSessionFactory().close();
    }
}
