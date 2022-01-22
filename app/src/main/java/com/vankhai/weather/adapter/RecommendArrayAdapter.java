package com.vankhai.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vankhai.weather.R;
import com.vankhai.weather.databinding.LocationRecommendItemBinding;
import com.vankhai.weather.model.LocationRecommend;

import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/*
 * Reference tutorial: https://learntodroid.com/android-autocompletetextview-tutorial-with-examples/
 */
public class RecommendArrayAdapter extends ArrayAdapter<LocationRecommend> {
    public RecommendArrayAdapter(@NonNull Context context, @NonNull List<LocationRecommend> recommends) {
        super(context, 0, recommends);
        this.recommends = recommends;
    }

    private List<LocationRecommend> recommends;
    private List<LocationRecommend> filteredLocations;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") LocationRecommendItemBinding binding = LocationRecommendItemBinding.inflate(inflater, parent, false);

        binding.locationNameTv.setText(recommends.get(position).getName());
        binding.countryTv.setText(recommends.get(position).getCountry());
        Timber.i("Toi dang duoc gọi ne ong vua long chua, nhung t lai eo duoc show ra, eo hieu tai sao");
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return recommends.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return locationFilter;
    }

    private Filter locationFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            filteredLocations = new LinkedList<>();

            filteredLocations.addAll(recommends);

            results.values = filteredLocations;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            if (((List) filterResults.values).size() != 0) {
                addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        }
    };
}
