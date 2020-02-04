package com.momeokji.moc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private Fragment currPage;
    private Fragment navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*- 초기 Fragment 등록 -*/
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.mainActivity_frameLayout, HomeFragment.newInstance());
        navigationBar = NavigationBarFragment.newInstance();
        fragmentTransaction.add(R.id.navigationBar_frameLayout, navigationBar);
        fragmentTransaction.commit();
        currPage = navigationBar;
    }

    /*- Fragment 교체 함수 -*/
    public void ReplaceFragment(Fragment targetFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainActivity_frameLayout, targetFragment).commit();
        setCurrPage(targetFragment);
    }

    public void ShowNavigationBar(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.anim_slide_in_bottom, R.animator.anim_slide_out_bottom);
        fragmentTransaction.show(navigationBar).commit();
    }
    public void HideNavigationBar(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.anim_slide_in_bottom, R.animator.anim_slide_out_bottom);
        fragmentTransaction.hide(navigationBar).commit();
    }

    public Fragment getCurrPage(){
        return this.currPage;
    }

    public void setCurrPage(Fragment page){
        this.currPage = page;
    }

}