import com.nk.apmt.ApartmentFilterHelper;

public class Main
{
    public static void main(String[] args)
    {
        String searchUrl = "https://www.apartments.com/sunnyvale-ca/min-2-bedrooms-under-3000";

//        if (args != null && args.length > 0)
//        {
//            searchUrl = args[0];
//        }

        ApartmentFilterHelper.start(searchUrl);

    }
}
