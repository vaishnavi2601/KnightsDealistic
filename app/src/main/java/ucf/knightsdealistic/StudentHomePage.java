package ucf.knightsdealistic;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ucf.knightsdealistic.database.datasource.BigDealDataSource;
import ucf.knightsdealistic.database.datasource.StoreDataSource;
import ucf.knightsdealistic.database.model.Deal;
import  ucf.knightsdealistic.database.datasource.DealDataSource;
import ucf.knightsdealistic.database.model.Store;


public class StudentHomePage extends ActionBarActivity {
    TabHost tabHost2;
    TextView set, bigdeal;
    TextView  logouttxt;
    String name;
    SQLiteDatabase db;
    DealDataSource dealDataSource;
    BigDealDataSource bigDealDataSource;
    StoreDataSource storeDataSource;
    TableLayout viewStoreDealsTable;
    TableLayout viewStoreDealsTablefuture;
    UserSessionManager session;
    String studentName;
    Button btnStudentSearch;
    EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //setContentView(R.layout.activity_student_home_page);
        setContentView(R.layout.activity_student_home_page);

      /*  Bundle extras = getIntent().getExtras();
        if(extras != null) {
            name = extras.getString("user");
        }*/
        session = new UserSessionManager(getApplicationContext());
        if(!session.isLogggedIn())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        studentName = user.get(UserSessionManager.KEY_NAME);
        viewStoreDealsTable=(TableLayout) this.findViewById( R.id.viewStoreDealsTable);
        viewStoreDealsTablefuture=(TableLayout) this.findViewById( R.id.viewStoreDealsTableFuture);
        dealDataSource = new DealDataSource(this);
        storeDataSource = new StoreDataSource(this);
        bigDealDataSource = new BigDealDataSource(this);

        set = (TextView) findViewById(R.id.helloStudentName);
        set.setText("Hello, "+ studentName);
        logouttxt = (TextView) findViewById(R.id.txtlogout);
      //  btnStudentSearch = (Button) findViewById(R.id.Searchbutton);
      //  searchbar = (EditText) this.findViewById(R.id.TxtSearch);
        tabHost2 = (TabHost) findViewById(R.id.tabHost1);

        bigdeal = (TextView) findViewById(R.id.bigDealOfTheDay);
     /*   KeyListener variable;
        variable = searchbar.getKeyListener();
        if(!searchbar.isInEditMode()){
            Toast.makeText(getApplicationContext(), String.valueOf(searchbar.isInEditMode()), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), variable.toString(), Toast.LENGTH_SHORT).show();
            searchbar.setEnabled(true);
        }

        if(searchbar.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } */
        bigdeal.setText("BIG DEAL of the DAY: ");
        try {
            bigdeal.append(bigDealDataSource.getBigDealOfTheDay().getBigDealDesc()+ " @ "+bigDealDataSource.getBigDealOfTheDay().getStore().getStoreName()+":"+ bigDealDataSource.getBigDealOfTheDay().getStore().getLocation());
        }
        catch (Exception e) {
            System.out.println("IT DID NOT WORK");
            e.printStackTrace();
        }

        bigdeal.setTextColor(Color.parseColor("#FFFFFF"));
        bigdeal.setTextSize(15);
        bigdeal.setTypeface(null, Typeface.BOLD);
        tabHost2.setup();
        TabHost.TabSpec  tabSpec= tabHost2.newTabSpec("Other Deals Of The Day");
        tabSpec.setContent(R.id.tabdealsoftheday);
        tabSpec.setIndicator("Other Deals Of The Day");
        tabHost2.addTab(tabSpec);


        tabSpec= tabHost2.newTabSpec("Upcoming Deals");
        tabSpec.setContent(R.id.tabupcomingdealsoftheday);
        tabSpec.setIndicator("Upcoming Deals");
        tabHost2.addTab(tabSpec);
      /*  Utility.setTabColor(tabHost2);


        tabHost2.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Utility.setTabColor(tabHost2);
            }
        }); */

        addRowsToTable(viewStoreDealsTable);
        addRowsToTableFuture(viewStoreDealsTablefuture);

/*
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        TabHost.TabSpec postDealTab = tabHost1.newTabSpec("Deals of the day");
        postDealTab.setContent(R.id.tabdealsoftheday);
        postDealTab.setIndicator("Deals of the day");
        tabHost1.addTab(postDealTab);


        TabHost.TabSpec postBigDealTab = tabHost1.newTabSpec("Upcoming deals");
        //postBigDealTab.setContent(R.id.tabupcomingdealsoftheday);
        postBigDealTab.setIndicator("Upcoming deals");
        tabHost1.addTab(postBigDealTab);
*/

/*
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        TabHost.TabSpec tab1 = tabHost1.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost1.newTabSpec("Second tab");
        tab1.setIndicator("Pending");
        tab1.setContent(R.id.tabdealsoftheday);

        tab2.setIndicator("Pending Big Deal Requests");
        tab2.setContent(R.id.tabupcomingdealsoftheday);

        tabHost1.addTab(tab1);
        tabHost1.addTab(tab2);

*/
        //populateListViewDeal();
        //populateListViewDeal();

     /*   btnStudentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentSearch = searchbar.getText().toString();
                Toast.makeText(getApplicationContext(), studentSearch, Toast.LENGTH_SHORT).show();
                try {
                    if (studentSearch.toString().equals("")) {

                        Toast.makeText(getApplicationContext(), "Passed to type anything", Toast.LENGTH_SHORT).show();
                    /*    Intent intent = new Intent(
                                StudentHomePage.this,
                                StudentSearch.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("user", name);
                        intent.putExtra("search", "work");
                        //intent.putExtra("Search",studentSearch);

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to type anything", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "It did not work", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }
        });*/

        logouttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, FirstScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addRowsToTable(TableLayout table) {
        TableRow header = new TableRow(this);
        TableRow.LayoutParams lptextview = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
/*        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("No");
        header.addView(tv);
*/
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Name");
        tv = new TextView(this);
        */
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Description");
        header.addView(tv);
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("From");
      header.addView(tv);
 */
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("To");
       header.addView(tv);
       */
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Edit");
        header.addView(tv);
        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Delete");
        header.addView(tv);
*/

        TableRow.LayoutParams lptablerow = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(lptablerow);
        header.setBackgroundColor(Color.parseColor("#F2D06A"));

        header.setHorizontalFadingEdgeEnabled(true);
        table.addView(header);

        List<Deal> deals = null;
        List<Store> store = null;
        try {
            deals = dealDataSource.getDealsOfTheDay();

            //deals = dealDataSource.getAllDeals();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int dealno = 1;
        for (final Deal deal : deals) {
            System.out.println(deal.getDealId());

            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.viewdealrowstudent, null);
            //((TextView) row.findViewById(R.id.txt_dealno)).setText(String.valueOf(dealno));
            //((TextView) row.findViewById(R.id.txt_dealname)).setText(deal.getDealName());
            //((TextView) row.findViewById(R.id.txt_dealdesc)).setText(deal.getDealName()+" - "+ deal.getDealDescription()+" -@ "+ deal.getStore().getLocation());   !!!!!!!!!!!!!!!!!!!!!!
            ((TextView) row.findViewById(R.id.txt_dealdesc)).setText(deal.getDealName()+" \n"+ deal.getDealDescription()+ " @  "+deal.getStore().getStoreName()+":"+deal.getStore().getLocation() +" ends by "+ DateUtility.dateToString(deal.getDealActiveTo()));
            //((TextView) row.findViewById(R.id.txt_from)).setText(DateUtility.dateToString(deal.getDealActiveFrom()));
            //nid((TextView) row.findViewById(R.id.txt_to)).setText(DateUtility.dateToString(deal.getDealActiveTo()));
            /*Button editbtn = (Button) row.findViewById(R.id.btn_edit);
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentHomePage.this, EditDeal.class);
                    intent.putExtra("clickedDealId", String.valueOf(deal.getDealId()));
                    startActivity(intent);
                }
            });*/
            table.addView(row);

            TableRow line = new TableRow(this);
            tv = new TextView(this);
            tv.setBackgroundColor(Color.parseColor("#F2D06A"));
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,3);
            lp.span = 4;
            tv.setLayoutParams(lp);

            line.addView(tv);

            table.addView(line);

        }
    }

    private void addRowsToTableFuture(TableLayout table) {
        TableRow header = new TableRow(this);
        TableRow.LayoutParams lptextview = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
/*        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("No");
        header.addView(tv);
*/
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Name");
        header.addView(tv);
        */
        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Description");
        header.addView(tv);
  /*     tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("From");
      header.addView(tv);

        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("To");
        header.addView(tv);
        */
/*        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Edit");
        header.addView(tv);
        tv = new TextView(this);
        tv.setWidth(80);
        tv.setLayoutParams(lptextview);
        tv.setText("Delete");
        header.addView(tv);
*/

        TableRow.LayoutParams lptablerow = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(lptablerow);
        header.setBackgroundColor(Color.parseColor("#F2D06A"));

        header.setHorizontalFadingEdgeEnabled(true);
        table.addView(header);

        List<Deal> deals = null;
        try {
            deals = dealDataSource.getAllUpcomingDealsByActiveFromTomorrow();
            //deals = dealDataSource.getAllDeals();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int dealno = 1;
        for (final Deal deal : deals) {
            System.out.println(deal.getDealId());

            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.viewdealrowsstudentfutre, null);
            //((TextView) row.findViewById(R.id.txt_dealno)).setText(String.valueOf(dealno));
            // ((TextView) row.findViewById(R.id.txt_dealname)).setText(deal.getDealName());

            ((TextView) row.findViewById(R.id.txt_dealdesc)).setText(deal.getDealName()+" \n"+deal.getDealDescription()+"  @  "+deal.getStore().getStoreName()+":"+ deal.getStore().getLocation()+"  From "+DateUtility.dateToString(deal.getDealActiveFrom())+" To "+DateUtility.dateToString(deal.getDealActiveTo()));
            // ((TextView) row.findViewById(R.id.txt_from)).setText(DateUtility.dateToString(deal.getDealActiveFrom()));
            //((TextView) row.findViewById(R.id.txt_to)).setText(DateUtility.dateToString(deal.getDealActiveTo()));
            /*Button editbtn = (Button) row.findViewById(R.id.btn_edit);
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentHomePage.this, EditDeal.class);
                    intent.putExtra("clickedDealId", String.valueOf(deal.getDealId()));
                    startActivity(intent);
                }
            });*/
            table.addView(row);

            TableRow line = new TableRow(this);
            tv = new TextView(this);
            tv.setBackgroundColor(Color.parseColor("#F2D06A"));
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,3);
            lp.span = 4;
            tv.setLayoutParams(lp);

            line.addView(tv);

            table.addView(line);

        }
    }


    private void populateListViewDeal() {

        List<Deal> deals = null;
        try {

            System.out.println("Tried");
            deals = dealDataSource.getDealsOfTheDay();
            System.out.println(deals.size());
        } catch (Exception e) {
            System.out.println("It failed");
            e.printStackTrace();
        }
        int dealno = 1;
        if(deals != null)
            for (final Deal deal : deals) {
                System.out.println("Deal id "+deal.getDealId());

                TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.viewdealsrow, null);
                ((TextView) row.findViewById(R.id.txt_dealno)).setText(String.valueOf(dealno));
                ((TextView) row.findViewById(R.id.txt_dealname)).setText(deal.getDealName());
                ((TextView) row.findViewById(R.id.txt_dealdesc)).setText(deal.getDealDescription());
                ((TextView) row.findViewById(R.id.txt_from)).setText(DateUtility.dateToString(deal.getDealActiveFrom()));
                ((TextView) row.findViewById(R.id.txt_to)).setText(DateUtility.dateToString(deal.getDealActiveTo()));
            }
        else
        {
            System.out.println("Deals is null");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
