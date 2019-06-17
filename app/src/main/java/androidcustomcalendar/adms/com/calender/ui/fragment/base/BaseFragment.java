package androidcustomcalendar.adms.com.calender.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidcustomcalendar.adms.com.calender.MainActivity;
import androidcustomcalendar.adms.com.calender.R;


public abstract class BaseFragment extends Fragment {

    protected View mContentView;
    protected MainActivity mHostActivity;
    private int mLayout;

    protected BaseFragment(@LayoutRes int layout) {
        mLayout = layout;
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return  mContentView.findViewById(id);
    }
    protected Toolbar findToolbar(@IdRes int id){
        return findViewById(id);
    }

    protected Toolbar findToolbar(){
        return findToolbar(R.id.toolbar);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mHostActivity = (MainActivity) context;
        }
    }

    protected ActionBar setToolbar(Toolbar toolbar) {
        FragmentActivity act = getActivity();
        AppCompatActivity activity;
        if (act instanceof AppCompatActivity) {
            activity = (AppCompatActivity) act;
            activity.setSupportActionBar(toolbar);
            return activity.getSupportActionBar();
        } else {
            throw new RuntimeException("Aktivitas ini tidak mewarisi AppCompatActivity");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(mLayout, container, false);
        setHasOptionsMenu(true);
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener((v, event) -> true);
    }


    /**
     * Tambahkan Fragmen baru ke bagian atas halaman
     *
     * @param fragment
     */
    protected void addFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_bottom, R.anim.in_from_bottom, R.anim.out_to_bottom)
                .add(R.id.main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    /**
     * Hapus Fragmen yang sesuai
     *
     * @param fragment
     */
    protected void removeFragment(Fragment fragment) {
        getFragmentManager().popBackStack(fragment.getClass().getSimpleName(), 1);
    }

    protected void showSnackbar(final String tip) {
        mHostActivity.runOnUiThread(() -> Snackbar.make(mContentView, tip, Snackbar.LENGTH_SHORT).show());
    }

    protected void showSnackbar(final String tip, final String action, final View.OnClickListener listener) {
        mHostActivity.runOnUiThread(() -> Snackbar.make(mContentView, tip, Snackbar.LENGTH_SHORT).setAction(action,listener).show());
    }

    /**
     * Atur UI Fragmen
     */
    protected abstract void setupUI();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHostActivity = null;
    }
}
