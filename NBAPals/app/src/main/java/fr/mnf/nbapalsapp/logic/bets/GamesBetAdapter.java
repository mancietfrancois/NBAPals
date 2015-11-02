package fr.mnf.nbapalsapp.logic.bets;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Francois on 25/10/2015.
 */
public class GamesBetAdapter extends FragmentStatePagerAdapter {

    public GamesBetAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MonthFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return Months.MONTHS_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Months.findMonthFromTabNumber(position).getMonthName();
    }
}
