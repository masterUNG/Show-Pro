package appewtc.masterung.showpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 11/10/2016 AD.
 */

public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] nameStrings, priceStrings, detailStrings,
            registStrings, periodStrings, dateRecordStrings;
    private TextView nameTextView, priceTextView, detailTextView,
            registTextView, periodTextView, dateRecordTextView;

    public MyAdapter(Context context,
                     String[] nameStrings,
                     String[] priceStrings,
                     String[] detailStrings,
                     String[] registStrings,
                     String[] periodStrings,
                     String[] dateRecordStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
        this.detailStrings = detailStrings;
        this.registStrings = registStrings;
        this.periodStrings = periodStrings;
        this.dateRecordStrings = dateRecordStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        //Bind Widget
        nameTextView = (TextView) view1.findViewById(R.id.textView);
        detailTextView = (TextView) view1.findViewById(R.id.textView3);
        priceTextView = (TextView) view1.findViewById(R.id.textView4);
        registTextView = (TextView) view1.findViewById(R.id.textView5);
        periodTextView = (TextView) view1.findViewById(R.id.textView6);
        dateRecordTextView = (TextView) view1.findViewById(R.id.textView7);

        //Show Text
        nameTextView.setText(nameStrings[i]);
        detailTextView.setText("Detail: " + detailStrings[i]);
        priceTextView.setText("Price: " + priceStrings[i]);
        registTextView.setText("วิธีการสมัคร: " + registStrings[i]);
        periodTextView.setText("ระยะเวลา: " + periodStrings[i]);
        dateRecordTextView.setText("เวลาที่บันทึก: " + dateRecordStrings[i]);

        return view1;
    }
}   // Main Class
