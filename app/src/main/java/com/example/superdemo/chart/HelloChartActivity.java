package com.example.superdemo.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.superdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.AbstractChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * HelloChartActivity 图表
 */
public class HelloChartActivity extends AppCompatActivity {

    @BindView(R.id.linechart)
    LineChartView mLinechart;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public enum ChartType {
        LINE_CHART, COLUMN_CHART, PIE_CHART, BUBBLE_CHART, PREVIEW_LINE_CHART, PREVIEW_COLUMN_CHART, OTHER
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hellochart);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content, new PlaceholderFragment()).commit();
        }

        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
//            Intent intent = new Intent(this, WaUIActivity.class);
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(getResources().getString(R.string.line_chart));
        mToolbar.setNavigationIcon(R.mipmap.back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setLineChart();

    }

    private void setLineChart() {

        mLinechart.setInteractive(true);
        mLinechart.setZoomType(ZoomType.VERTICAL);
        mLinechart.setContainerScrollEnabled(true, ContainerScrollType.VERTICAL);

//        ChartData.setAxisXBottom(Axis axisX);
//        ColumnChartData.setStacked(boolean isStacked);
//        Line.setStrokeWidth(int strokeWidthDp);

        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

//        LineChartView chart = new LineChartView(getApplicationContext());
        mLinechart.setLineChartData(data);
    }

    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {

        @BindView(android.R.id.list)
        ListView mListView;
        ChartSamplesAdapter mAdapter;

        public PlaceholderFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, rootView);
            mAdapter = new ChartSamplesAdapter(getActivity(), 0, generateSamplesDescriptions());
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(this);
            return rootView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;

            switch (position) {
                case 0:
                    // Line Chart;
                    intent = new Intent(getActivity(), LineChartActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    // Column Chart;
//                    intent = new Intent(getActivity(), ColumnChartActivity.class);
//                    startActivity(intent);
                    break;
                case 2:
                    // Pie Chart;
//                    intent = new Intent(getActivity(), PieChartActivity.class);
//                    startActivity(intent);
                    break;
                case 3:
                    // Bubble Chart;
//                    intent = new Intent(getActivity(), BubbleChartActivity.class);
//                    startActivity(intent);
                    break;
                case 4:
                    // Preview Line Chart;
//                    intent = new Intent(getActivity(), PreviewLineChartActivity.class);
//                    startActivity(intent);
                    break;
                case 5:
                    // Preview Column Chart;
//                    intent = new Intent(getActivity(), PreviewColumnChartActivity.class);
//                    startActivity(intent);
                    break;
                case 6:
                    // Combo Chart;
//                    intent = new Intent(getActivity(), ComboLineColumnChartActivity.class);
//                    startActivity(intent);
                    break;
                case 7:
                    // Line Column Dependency;
//                    intent = new Intent(getActivity(), LineColumnDependencyActivity.class);
//                    startActivity(intent);
                    break;
                case 8:
                    // Tempo line chart;
//                    intent = new Intent(getActivity(), TempoChartActivity.class);
//                    startActivity(intent);
                    break;
                case 9:
                    // Speed line chart;
//                    intent = new Intent(getActivity(), SpeedChartActivity.class);
//                    startActivity(intent);
                    break;
                case 10:
                    // Good Bad filled line chart;
//                    intent = new Intent(getActivity(), GoodBadChartActivity.class);
//                    startActivity(intent);
                    break;
                case 11:
                    // Good Bad filled line chart;
//                    intent = new Intent(getActivity(), ViewPagerChartsActivity.class);
//                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

        private List<ChartSampleDescription> generateSamplesDescriptions() {
            List<ChartSampleDescription> list = new ArrayList<>();

            list.add(new ChartSampleDescription("Line Chart", "", ChartType.LINE_CHART));
            list.add(new ChartSampleDescription("Column Chart", "", ChartType.COLUMN_CHART));
            list.add(new ChartSampleDescription("Pie Chart", "", ChartType.PIE_CHART));
            list.add(new ChartSampleDescription("Bubble Chart", "", ChartType.BUBBLE_CHART));
            list.add(new ChartSampleDescription("Preview Line Chart",
                    "Control line chart viewport with another line chart.", ChartType.PREVIEW_LINE_CHART));
            list.add(new ChartSampleDescription("Preview Column Chart",
                    "Control column chart viewport with another column chart.", ChartType.PREVIEW_COLUMN_CHART));
            list.add(new ChartSampleDescription("Combo Line/Column Chart", "Combo chart with lines and columns.",
                    ChartType.OTHER));
            list.add(new ChartSampleDescription("Line/Column Chart Dependency",
                    "LineChart responds(with animation) to column chart value selection.", ChartType.OTHER));
            list.add(new ChartSampleDescription(
                    "Tempo Chart",
                    "Presents tempo and height values on a signle chart. Example of multiple axes and reverted Y axis" +
                            " with time format [mm:ss].",
                    ChartType.OTHER));
            list.add(new ChartSampleDescription("Speed Chart",
                    "Presents speed and height values on a signle chart. Exapmle of multiple axes inside chart area.",
                    ChartType.OTHER));
            list.add(new ChartSampleDescription("Good/Bad Chart",
                    "Example of filled area line chart with custom labels", ChartType.OTHER));
            list.add(new ChartSampleDescription("ViewPager with Charts",
                    "Interactive charts within ViewPager. Each chart can be zoom/scroll except pie chart.",
                    ChartType.OTHER));

            return list;
        }
    }

    public static class ChartSamplesAdapter extends ArrayAdapter<ChartSampleDescription> {

        public ChartSamplesAdapter(Context context, int resource, List<ChartSampleDescription> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sample, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ChartSampleDescription item = getItem(position);

            viewHolder.chartLayout.setVisibility(View.VISIBLE);
            viewHolder.chartLayout.removeAllViews();

            AbstractChartView chart;
            switch (item.chartType) {
                case LINE_CHART:
                    chart = new LineChartView(getContext());
                    viewHolder.chartLayout.addView(chart);
            }

            return convertView;
        }

        static class ViewHolder {
            @BindView(R.id.text1)
            TextView text1;
            @BindView(R.id.text2)
            TextView text2;
            @BindView(R.id.chart_layout)
            FrameLayout chartLayout;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


    public static class ChartSampleDescription {
        String text1;
        String text2;
        ChartType chartType;

        public ChartSampleDescription(String text1, String text2, ChartType chartType) {
            this.text1 = text1;
            this.text2 = text2;
            this.chartType = chartType;
        }
    }
}
