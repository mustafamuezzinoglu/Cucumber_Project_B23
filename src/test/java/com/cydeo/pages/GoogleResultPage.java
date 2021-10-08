package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * * - Google SearchResultPage
 *      *   - Fields
 *      *      searchResultCount
 *      *      resultLinks (list of WebElement )
 *      *   - Methods
 *      *      getResultCountText
 *      *      getAllResultLinkText
 */

public class GoogleResultPage {
    @FindBy(id = "result-stats")
    private WebElement searchResultCount;

    @FindBy(xpath = "//h3[@class='LC20lb DKV0Md']")
    private List<WebElement> resultLinks;
    //h3[@class='LC20lb DKV0Md']

    public GoogleResultPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public String getResultCountText(){

        return searchResultCount.getText();
    }



    /**
     *  Print out the text of all the resulting link
     */
    public void printAllSearchResultLinks(){
        System.out.println("resultLinks.size() = " + resultLinks.size());
        for (WebElement eachLink : resultLinks) {
            //remove empty text with if statement
            //System.out.println(eachLink.getText());
            if(eachLink.getText().isEmpty()) {
                continue;
            } System.out.println("eachLink.getText() = " + eachLink.getText());
        }

    }



}
