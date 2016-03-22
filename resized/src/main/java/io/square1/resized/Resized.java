package io.square1.resized;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import org.json.JSONObject;

import java.util.StringTokenizer;

/**
 * Created by roberto on 22/03/2016.
 */
public final class Resized {


    public static Resized init(String key, String secret){
        return new Resized(key, secret);
    }


    private final String mKey;
    private final String mSecret;

    private String mHost;
    private String mDefaultImage;

    private Resized(String key, String secret){
        mKey = key;
        mSecret = secret;
        mHost = "https://img.resized.co";
    }


    /**
     * Override default host
     * @param host
     */
    public void setHost(String host){

        if(TextUtils.isEmpty(host) == false){
            mHost = host;
        }
    }

    /**
     * set the url for a default image
     * @param defaultImage
     */
    public void setDefaultImage(String defaultImage){
        mDefaultImage = defaultImage;
    }


    public String process(Uri uri, int width, int height ) {
        return process(uri.toString(), width, height);
    }

    public String process(String imageUrl, int width, int height ) {

        if(validURL(imageUrl) == false){
            imageUrl = mDefaultImage;
        }

        if(validURL(imageUrl) == true ){

            JSONObject data = new JSONObject();

            try {

                data.put("url", imageUrl);

                if(width > 0) data.put("width", width);
                if(height > 0) data.put("height", height);

                if(validURL(mDefaultImage) == true){
                    data.put("default", mDefaultImage);
                }

                /// get the sha1
                String stringData = data.toString();
                String hash = StringUtils.SHA1(mKey + mSecret + stringData);

                JSONObject encodedData = new JSONObject();
                encodedData.put("data", stringData);
                encodedData.put("hash", hash);
                stringData = encodedData.toString();

                String uri = Base64.encodeToString(stringData.getBytes(),  Base64.NO_WRAP | Base64.URL_SAFE );
                Uri.Builder request = Uri.parse(mHost).buildUpon();
                request.appendPath(mKey).appendEncodedPath(uri);

                return request.build().toString();

            }catch (Exception e){
                return imageUrl;
            }

        }

        return imageUrl;
    }

    public String processW(String imageUrl, int width ) {
        return process(imageUrl, width , -1);
    }

    public String processH(String imageUrl, int height ) {
        return process(imageUrl, -1 , height);
    }


    private boolean validURL(String url) {

        try {

           Uri result = Uri.parse(url);
           return result != null &&
                   Uri.EMPTY.equals(result) == false;

        }catch (Exception e){
            return false;
        }
    }
}
