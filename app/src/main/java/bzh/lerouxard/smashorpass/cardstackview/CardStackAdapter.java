package bzh.lerouxard.smashorpass.cardstackview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import bzh.lerouxard.smashorpass.R;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<Spot> spots;

    public CardStackAdapter(List<Spot> spots) {
        this.spots = spots;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_spot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Spot spot = spots.get(position);
        holder.name.setText(spot.getName());
        holder.city.setText(spot.getCity());
        Glide.with(holder.image)
                .load(spot.getUrl())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), spot.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return spots.size();
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void addSpot(Spot spot) {
        spots.add(spot);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView city;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_name);
            city = view.findViewById(R.id.item_city);
            image = view.findViewById(R.id.item_image);
        }
    }
}
