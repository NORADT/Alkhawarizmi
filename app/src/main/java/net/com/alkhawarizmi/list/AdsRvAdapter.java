package net.com.alkhawarizmi.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.com.alkhawarizmi.R;
import net.com.alkhawarizmi.models.Ad;

import java.util.List;


public class AdsRvAdapter extends RecyclerView.Adapter<AdsRvAdapter.ViewHolder> {

    public List<Ad> adList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public TextView courseNo;
        public TextView category;
        public TextView subCategory;
        public TextView price;
        public TextView date;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            courseNo = view.findViewById(R.id.courseNo);
            category = view.findViewById(R.id.category);
            subCategory = view.findViewById(R.id.subCat);
            price = view.findViewById(R.id.price);
            date = view.findViewById(R.id.date);
            image = view.findViewById(R.id.image);
        }
    }

    public Ad getValueAt(int position) {
        return adList.get(position);
    }

    public AdsRvAdapter(List<Ad> serviceList) {
        this.adList = serviceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ad_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Ad ad = getValueAt(position);

        holder.courseNo.setText(ad.getCourseNo());
        holder.category.setText(ad.getCategory());
        holder.subCategory.setText(ad.getSubCategory());
        holder.price.setText(ad.getPrice());
        holder.date.setText(ad.getDate());

        holder.image.setImageResource(ad.getImageId());
    }
}
