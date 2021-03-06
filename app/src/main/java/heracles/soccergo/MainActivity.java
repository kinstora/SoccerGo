package heracles.soccergo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import heracles.soccergo.club.ClubFragment;
import heracles.soccergo.home.HomeFragment;
import heracles.soccergo.more.MoreFragment;
import heracles.soccergo.race.RaceFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView ivRace;
    private ImageView ivClub;
    private ImageView ivHome;
    private ImageView ivCommunity;
    private ImageView ivMore;
    private LinearLayout llMainLayout;

    private Fragment clubFragment;
    private Fragment homeFragment;
    private Fragment moreFragment;
    private Fragment raceFragment;
    private int currentFragmentID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
    }

    private void initWidget()
    {
        getWidget();
        setWidget();
        new GetFragment().execute();
    }

    private void setWidget()
    {
        ivRace.setOnClickListener(this);
        ivClub.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        ivCommunity.setOnClickListener(this);
        ivMore.setOnClickListener(this);
    }

    private void getWidget()
    {
        ivRace = (ImageView) findViewById(R.id.ivRace);
        ivClub = (ImageView) findViewById(R.id.ivClub);
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivCommunity = (ImageView) findViewById(R.id.ivCommunity);
        ivMore = (ImageView) findViewById(R.id.ivMore);
        llMainLayout = (LinearLayout) findViewById(R.id.llMainLayout);
    }

    @Override
    public void onClick(View v)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (currentFragmentID)
        {
            case R.id.ivRace:
                ft.hide(raceFragment);
                break;
            case R.id.ivClub:
                ft.hide(clubFragment);
                break;
            case R.id.ivHome:
                ft.hide(homeFragment);
                break;
            case R.id.ivCommunity:
                break;
            case R.id.ivMore:
                ft.hide(moreFragment);
                break;
        }

        switch (v.getId())
        {
            case R.id.ivRace:
                ft.show(raceFragment);
                currentFragmentID = R.id.ivRace;
                break;
            case R.id.ivClub:
                ft.show(clubFragment);
                currentFragmentID = R.id.ivClub;
                break;
            case R.id.ivHome:
                ft.show(homeFragment);
                currentFragmentID = R.id.ivHome;
                break;
            case R.id.ivCommunity:
                break;
            case R.id.ivMore:
                ft.show(moreFragment);
                currentFragmentID = R.id.ivMore;
                break;
        }

        ft.commit();
    }

    // 异步获取
    class GetFragment extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            clubFragment = new ClubFragment();
            homeFragment = new HomeFragment();
            moreFragment = new MoreFragment();
            raceFragment = new RaceFragment();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.llMainLayout,homeFragment, "homeFragment").add(R.id.llMainLayout,clubFragment, "clubFragment")
                    .add(R.id.llMainLayout,moreFragment, "moreFragment").add(R.id.llMainLayout,raceFragment, "raceFragment");
            ft.show(homeFragment).hide(clubFragment).hide(moreFragment).hide(raceFragment).commit();
            currentFragmentID = R.id.ivHome;
        }
    }
}