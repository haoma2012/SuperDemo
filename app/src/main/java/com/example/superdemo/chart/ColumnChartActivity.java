package com.example.superdemo.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * ColumnChartActivity
 */
public class ColumnChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_line_chart);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        @BindView(R.id.toolbar)
        Toolbar mToolbar;
        @BindView(R.id.chart)
        ColumnChartView mColumnchart;
        AppCompatActivity compatActivity;

        private static final int DEFAULT_DATA = 0;
        private static final int SUBCOLUMNS_DATA = 1;
        private static final int STACKED_DATA = 2;
        private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
        private static final int NEGATIVE_STACKED_DATA = 4;

        private ColumnChartData data;
        private boolean hasAxes = true;
        private boolean hasAxesNames = true;
        private boolean hasLabels = false;
        private boolean hasLabelForSelected = false;
        private int dataType = DEFAULT_DATA;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_column_chart, container, false);

            ButterKnife.bind(this, rootView);
            compatActivity = (AppCompatActivity) getActivity();
            compatActivity.setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);

            mColumnchart.setOnValueTouchListener(new ValueTouchListener());

            generateData();
            return rootView;
        }


        private void generateData() {
            switch (dataType) {
                case DEFAULT_DATA:
                    generateDefaultData();
                    break;
                case SUBCOLUMNS_DATA:
                    generateSubcolumnsData();
                    break;
                case STACKED_DATA:
                    generateStackedData();
                    break;
                case NEGATIVE_SUBCOLUMNS_DATA:
                    generateNegativeSubcolumnsData();
                    break;
                case NEGATIVE_STACKED_DATA:
                    generateNegativeStackedData();
                    break;
                default:
                    generateDefaultData();
                    break;
            }

        }

        //default data 条线图只有一个子状
        private void generateDefaultData() {
            int numSubcolumns = 1;
            int numColumns = 8;
            // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;

            for (int i = 0; i < numColumns; ++i) {
                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }
            data = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            mColumnchart.setColumnChartData(data);
        }

        /**
         * Generates columns with subcolumns, columns have larger separation than subcolumns.
         */
        private void generateSubcolumnsData() {
            int numSubcolumns = 4;
            int numColumns = 4;
            // Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            mColumnchart.setColumnChartData(data);

        }

        /**
         * Generates columns with stacked subcolumns.
         */
        private void generateStackedData() {
            int numSubcolumns = 4;
            int numColumns = 8;
            // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) Math.random() * 20f + 5, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            // Set stacked flag.
            data.setStacked(true);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            mColumnchart.setColumnChartData(data);

        }

        private void generateNegativeSubcolumnsData() {
            int numSubcolumns = 4;
            int numColumns = 4;
            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    int sign = getSign();
                    values.add(new SubcolumnValue((float) Math.random() * 50f * sign + 5 * sign, ChartUtils.pickColor
                            ()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            mColumnchart.setColumnChartData(data);
        }

        private void generateNegativeStackedData() {
            int numSubcolumns = 4;
            int numColumns = 8;
            // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    int sign = getSign();
                    values.add(new SubcolumnValue((float) Math.random() * 20f * sign + 5 * sign, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            // Set stacked flag.
            data.setStacked(true);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            mColumnchart.setColumnChartData(data);
        }

        private int getSign() {
            int[] sign = new int[]{-1, 1};
            return sign[Math.round((float) Math.random())];
        }

        private void reset() {
            hasAxes = true;
            hasAxesNames = true;
            hasLabels = false;
            hasLabelForSelected = false;
            dataType = DEFAULT_DATA;
            mColumnchart.setValueSelectionEnabled(hasLabelForSelected);
        }

        private void toggleLabels() {
            hasLabels = !hasLabels;

            if (hasLabels) {
                hasLabelForSelected = false;
                mColumnchart.setValueSelectionEnabled(hasLabelForSelected);
            }
            generateData();
        }

        private void toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected;
            mColumnchart.setValueSelectionEnabled(hasLabelForSelected);

            if (hasLabelForSelected) {
                hasLabels = false;
            }
            generateData();
        }

        private void toggleAxes() {
            hasAxes = !hasAxes;
            generateData();
        }

        private void toggleAxesNames() {
            hasAxesNames = !hasAxesNames;
            generateData();
        }

        /**
         * To animate values you have to change targets values and then call { Chart#startDataAnimation()}
         * method(don't confuse with View.animate()).
         */
        private void prepareDataAnimation() {
            for (Column column : data.getColumns()) {
                for (SubcolumnValue value : column.getValues()) {
                    value.setTarget((float) Math.random() * 100);
                }
            }
        }

        private class ValueTouchListener implements ColumnChartOnValueSelectListener {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                Toast.makeText(getActivity(), "Selected: " + subcolumnValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.column_chart, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_reset) {
                reset();
                generateData();
                return true;
            }
            if (id == R.id.action_subcolumns) {
                dataType = SUBCOLUMNS_DATA;
                generateData();
                return true;
            }
            if (id == R.id.action_stacked) {
                dataType = STACKED_DATA;
                generateData();
                return true;
            }
            if (id == R.id.action_negative_subcolumns) {
                dataType = NEGATIVE_SUBCOLUMNS_DATA;
                generateData();
                return true;
            }
            if (id == R.id.action_negative_stacked) {
                dataType = NEGATIVE_STACKED_DATA;
                generateData();
                return true;
            }
            if (id == R.id.action_toggle_labels) {
                toggleLabels();
                return true;
            }
            if (id == R.id.action_toggle_axes) {
                toggleAxes();
                return true;
            }
            if (id == R.id.action_toggle_axes_names) {
                toggleAxesNames();
                return true;
            }
            if (id == R.id.action_animate) {
                prepareDataAnimation();
                mColumnchart.startDataAnimation();
                return true;
            }
            if (id == R.id.action_toggle_selection_mode) {
                toggleLabelForSelected();

                Toast.makeText(getActivity(),
                        "Selection mode set to " + mColumnchart.isValueSelectionEnabled() + " select any point.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_toggle_touch_zoom) {
                mColumnchart.setZoomEnabled(!mColumnchart.isZoomEnabled());
                Toast.makeText(getActivity(), "IsZoomEnabled " + mColumnchart.isZoomEnabled(), Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_zoom_both) {
                mColumnchart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
                return true;
            }
            if (id == R.id.action_zoom_horizontal) {
                mColumnchart.setZoomType(ZoomType.HORIZONTAL);
                return true;
            }
            if (id == R.id.action_zoom_vertical) {
                mColumnchart.setZoomType(ZoomType.VERTICAL);
                return true;
            }


            return super.onOptionsItemSelected(item);
        }
    }

}
