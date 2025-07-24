package net.com.alkhawarizmi;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.com.alkhawarizmi.models.Ad;

import java.util.List;


public class HomevAdapter extends RecyclerView.Adapter<HomevAdapter.ViewHolder> {

    public List<Ad> adList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public TextView category;
        public TextView date;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            category = view.findViewById(R.id.desc);
            date = view.findViewById(R.id.date);
        }
    }

    public Ad getValueAt(int position) {
        return adList.get(position);
    }

    public HomevAdapter(List<Ad> serviceList) {
        this.adList = serviceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_ad_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Ad ad = getValueAt(position);

        Typeface face = Typeface.createFromAsset(holder.category.getContext().getAssets(),
                "fonts/Almarai-Regular.ttf");


        holder.category.setText(ad.getCategory());
        holder.category.setTypeface(face);
        holder.date.setText(ad.getDate());
        holder.date.setTypeface(face);
    }
}
