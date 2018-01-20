package example.study.com.myapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：
 */

public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_FOOTER = 1;
    public static final int VIEW_TYPE_ITEM_LOADED = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    public static final int VIEW_TYPE_FAILED = 4;

    private LinkedHashMap<String, Section> sections;
    private HashMap<String, Integer> sectionViewTypeNumbers;
    private int viewTypeCount = 0;
    private static final int VIEW_TYPE_QTY = 5;

    public SectionedRecyclerViewAdapter() {
        sections = new LinkedHashMap<>();
        sectionViewTypeNumbers = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        for (Map.Entry<String, Integer> entry : sectionViewTypeNumbers.entrySet()) {
            if (viewType >= entry.getValue() && viewType < entry.getValue() + VIEW_TYPE_QTY) {
                Section section = sections.get(entry.getKey());
                int sectionViewType = viewType - entry.getValue();
                switch (sectionViewType) {
                    case VIEW_TYPE_HEADER: {
                        Integer resId = section.getHeaderResourceId();
                        if (resId == null) {
                            throw new NullPointerException("missing 'header' resource id");
                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
                        viewHolder = section.getHeaderViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_FOOTER: {
                        Integer resId = section.getFooterResourceId();
                        if (resId == null) {
                            throw new NullPointerException("miss 'footer' resource id");
                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
                        viewHolder =section.getFooterViewHolder(view);
                        break;
                    }

                }
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
