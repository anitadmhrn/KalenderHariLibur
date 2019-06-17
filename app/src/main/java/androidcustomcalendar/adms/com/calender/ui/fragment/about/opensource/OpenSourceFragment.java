package androidcustomcalendar.adms.com.calender.ui.fragment.about.opensource;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.entity.OpenSource;
import androidcustomcalendar.adms.com.calender.ui.adapter.opensource.OpenSourceAdapter;
import androidcustomcalendar.adms.com.calender.ui.fragment.base.BaseFragment;


public class OpenSourceFragment extends BaseFragment implements OpenSourceAdapter.OnOpenSourceClickListener {

    private List<OpenSource> mOpenSources = new ArrayList<>();

    public OpenSourceFragment() {
        super(R.layout.fragment_open_source);
    }

    public static OpenSourceFragment newInstance() {
        OpenSourceFragment fragment = new OpenSourceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupUI() {
        setOpenSources();
        RecyclerView rv = findViewById(R.id.rv_os);
        OpenSourceAdapter openSourceAdapter = new OpenSourceAdapter(mOpenSources, this);
        rv.setAdapter(openSourceAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        findToolbar().setNavigationOnClickListener(v -> removeFragment(OpenSourceFragment.this));
    }

    @Override
    public void onOpenSourceClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void setOpenSources(){
        OpenSource os0 = new OpenSource();
        os0.setTitle("ExpandableLayout");
        os0.setDetail("An Android layout class that supports animating the expansion and collapse of its child views.");
        os0.setUrl("https://github.com/cachapa/ExpandableLayout/blob/master/LICENSE.txt");
        mOpenSources.add(os0);

        OpenSource os1 = new OpenSource();
        os1.setTitle("CircleProgressbar");
        os1.setDetail("CircleProgressBar mewarisi ProgressBar, yang merupakan bilah kemajuan cincin dengan gaya garis solid dan solid.Selain itu, nilai progres dapat disesuaikan sesuai keinginan. ");
        os1.setUrl("https://github.com/dinuscxj/CircleProgressBar");
        mOpenSources.add(os1);

        OpenSource os2 = new OpenSource();
        os2.setTitle("OkHttp");
        os2.setDetail("HTTP is the way modern applications network. It’s how we exchange data & media.");
        os2.setUrl("http://square.github.io/okhttp/");
        mOpenSources.add(os2);

        OpenSource os3 = new OpenSource();
        os3.setTitle("Gson");
        os3.setDetail("Gson is a Java library that can be used to convert Java Objects into their JSON representation. ");
        os3.setUrl("https://github.com/google/gson/blob/master/LICENSE");
        mOpenSources.add(os3);

    }
}
