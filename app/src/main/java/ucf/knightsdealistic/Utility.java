package ucf.knightsdealistic;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TabHost;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Swathi on 4/8/2015.
 */
public class Utility  {

    //public static String bodyofmailForRegisterAccept= "Hi,"+store.getStoreName()"\n"+"We are happy to inform you that your store registration request is accepted.You are eligible to post deals now".

    public static void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

    public static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F2D06A")); // selected
    }
}
