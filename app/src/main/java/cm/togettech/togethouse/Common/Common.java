package cm.togettech.togethouse.Common;

import cm.togettech.togethouse.Model.AddonModel;
import cm.togettech.togethouse.Model.AppartementModel;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.Model.StudioModel;
import cm.togettech.togethouse.Model.SizeModel;
import cm.togettech.togethouse.Model.UserModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Common {
    public static final String USER_REFERENCESER = "Users";
    public static final String APPARTEMENT_REF = "Appartements";

    public static final int DEFAULT_COLUMN_COUNT = 0;
    public static final int FULL_WIDTH_COLUMN = 1;

    public static final String STUDIO_REF = "Studios";
    public static final String CHAMBRE_REF = "Chambres";




    public static final String COMMENT_REF = "Comments";



    public static UserModel currentUser;
    public static StudioModel studioSelected;

    public static ChambreModel selectedChambre;
    public static AppartementModel selectedAppartement;



    public static UserModel userSelected;


    public static String formatPrice(double price){
        if (price != 0){
            DecimalFormat df = new DecimalFormat("#,##0.00");
            df.setRoundingMode(RoundingMode.UP);
            String finalPrice = new StringBuilder(df.format(price)).toString();
            return finalPrice.replace(".", ",");
        }
        else
            return "0,00";
    }

    public static Double calculateExtraPrice(SizeModel userSelectedSize, List<AddonModel> userSelectedAddon) {
        Double result = 0.0;
        if (userSelectedSize == null && userSelectedAddon == null)
            return 0.0;
        else if (userSelectedSize == null){
            for (AddonModel addonModel : userSelectedAddon)
                result+=addonModel.getPrice();
            return result;
        }else if (userSelectedAddon == null){
            return userSelectedSize.getPrice()*1.0;
        } else {
            result = userSelectedSize.getPrice()*1.0;
            for (AddonModel addonModel : userSelectedAddon)
                result+=addonModel.getPrice();
            return result;
        }
    }
}
