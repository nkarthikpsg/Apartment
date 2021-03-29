package com.nk.apmt;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nk.apmt.dto.ApartmentDetail;
import com.nk.apmt.dto.ApartmentList;

public class ApartmentFilter
{

    public ApartmentFilter()
    {

    }

    public ApartmentList start(String searchUrl)
    {
        ApartmentList apartmentList = new ApartmentList();

        for(int i = 1; i <= 5; i++)
        {
            Document document = getDocument(searchUrl, i);

            Elements contents = document.select("li[class=mortar-wrapper]");

            for (Element content : contents)
            {
                ApartmentDetail apartmentDetail = getApmtDetail(content);
                apartmentList.addApartment(apartmentDetail);
            }

        }

        System.out.println(apartmentList);

        return apartmentList;

    }

    private ApartmentDetail getApmtDetail(Element con)
    {
        ApartmentDetail aparmentDetail = null;

        if(con.select("div[class=price-range]").first() != null)
        {

            aparmentDetail = new ApartmentDetail();

            aparmentDetail.setName(con.select("span[class=js-placardTitle title]").first().text());

            aparmentDetail.setPriceRange(con.select("div[class=price-range]").first().text());
            aparmentDetail.setDetailUrl(con.select("a[class=property-link]").first().attr("href"));

            populateApmtDetail(aparmentDetail);
        }

        return aparmentDetail;
    }

    private void populateApmtDetail(ApartmentDetail aparmentDetail)
    {
        String detailUrl = aparmentDetail.getDetailUrl();

        Document document = getDocument(detailUrl);

        Elements images = document.select("li[class=item paidImageLarge]");

        images.stream().findFirst().ifPresent(
                image -> aparmentDetail.setImageUrl(image.select("div[class=aspectRatioImage]").first().attr("style")));

        String schoolRating = null;
        String schoolName = null;
        Elements schoolCards = document.select("div[class=schoolCard]");
        for (Element schoolCard : schoolCards)
        {
            if ("Public Elementary School".equals(schoolCard.select("p[class=schoolType]").text()))
            {
                Element schoolZone = schoolCard.selectFirst("div[class=nearbySchools]");
                if (schoolZone.children().stream().anyMatch(e -> e.hasClass("attendanceZoneIcon")))
                {
                    Element ratingElement = schoolCard.selectFirst("div[class=schoolRating]");
                    schoolRating = ratingElement.children().stream().filter(e -> e.nodeName().equals("i")).findFirst()
                            .get().attr("class");

                    Element nameElement = schoolCard.selectFirst("h3[class=schoolName]");
                    schoolName = nameElement.selectFirst("a").text();

                    break;
                }
            }
        }
        if (schoolName != null)
        {
            aparmentDetail.setEleSchoolRating(schoolRating.replace("rating", "").replace("gsIcon", ""));
            aparmentDetail.setEleSchoolName(schoolName);
        }

    }

    private Document getDocument(String url)
    {
        return getDocument(url, 1);
    }

    private Document getDocument(String url, int i)
    {
        Document doc;

        if(i != 1)
        {
            url = url + "/" + i;
        }

        try
        {
            doc = Jsoup.connect(url).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return doc;
    }

    public static InputStream loadContentByHttpClient(String url) throws ClientProtocolException, IOException
    {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        return response.getEntity().getContent();
    }

}
