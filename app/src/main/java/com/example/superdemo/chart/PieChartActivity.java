package com.example.superdemo.chart;

import android.graphics.Typeface;
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
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2016/6/29.
 */
public class PieChartActivity extends AppCompatActivity {

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
        PieChartView mPieChart;
        private AppCompatActivity appCompatActivity;

        private PieChartData data;

        private boolean hasLabels = false;
        private boolean hasLabelsOutside = false;
        private boolean hasCenterCircle = false;
        private boolean hasCenterText1 = false;
        private boolean hasCenterText2 = false;
        private boolean isExploded = false;
        private boolean hasLabelForSelected = false;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

            ButterKnife.bind(this, view);
            appCompatActivity = (AppCompatActivity) getActivity();
            appCompatActivity.setSupportActionBar(mToolbar);

            setHasOptionsMenu(true);
            mPieChart.setOnValueTouchListener(new ValueTouchListener());

            generateData();
            return view;
        }

        private void generateData() {
            int numValues = 6;

            List<SliceValue> values = new ArrayList<>();
            for (int i = 0; i < numValues; ++i) {
                SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
                values.add(sliceValue);
            }

            data = new PieChartData(values);
            data.setHasLabels(hasLabels);
            data.setHasLabelsOnlyForSelected(hasLabelForSelected);
            data.setHasLabelsOutside(hasLabelsOutside);
            data.setHasCenterCircle(hasCenterCircle);

            if (isExploded) {
                data.setSlicesSpacing(24);
            }

            if (hasCenterText1) {
                data.setCenterText1("Hello!");

                // Get roboto-italic font.
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
                data.setCenterText1Typeface(tf);

                // Get font size from dimens.xml and convert it to sp(library uses sp values).
                data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
            }

            if (hasCenterText2) {
                data.setCenterText2("Charts (Roboto Italic)");

                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");

                data.setCenterText2Typeface(tf);
                data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
            }

            mPieChart.setPieChartData(data);
        }

        private void reset() {
            mPieChart.setCircleFillRatio(1.0f);
            hasLabels = false;
            hasLabelsOutside = false;
            hasCenterCircle = false;
            hasCenterText1 = false;
            hasCenterText2 = false;
            isExploded = false;
            hasLabelForSelected = false;
        }

        private void explodeChart() {
            isExploded = !isExploded;
            generateData();

        }

        private void toggleLabelsOutside() {
            // has labels have to be true:P
            hasLabelsOutside = !hasLabelsOutside;
            if (hasLabelsOutside) {
                hasLabels = true;
                hasLabelForSelected = false;
                mPieChart.setValueSelectionEnabled(hasLabelForSelected);
            }

            if (hasLabelsOutside) {
                mPieChart.setCircleFillRatio(0.7f);
            } else {
                mPieChart.setCircleFillRatio(1.0f);
            }

            generateData();

        }

        private void toggleLabels() {
            hasLabels = !hasLabels;

            if (hasLabels) {
                hasLabelForSelected = false;
                mPieChart.setValueSelectionEnabled(hasLabelForSelected);

                if (hasLabelsOutside) {
                    mPieChart.setCircleFillRatio(0.7f);
                } else {
                    mPieChart.setCircleFillRatio(1.0f);
                }
            }

            generateData();
        }

        private void toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected;

            mPieChart.setValueSelectionEnabled(hasLabelForSelected);

            if (hasLabelForSelected) {
                hasLabels = false;
                hasLabelsOutside = false;

                if (hasLabelsOutside) {
                    mPieChart.setCircleFillRatio(0.7f);
                } else {
                    mPieChart.setCircleFillRatio(1.0f);
                }
            }

            generateData();
        }

        /**
         * To animate values you have to change targets values and then call {Chart#startDataAnimation()}
         * method(don't confuse with View.animate()).
         */
        private void prepareDataAnimation() {
            for (SliceValue value : data.getValues()) {
                value.setTarget((float) Math.random() * 30 + 15);
            }
        }

        // MENU
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.pie_chart, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_reset) {
                reset();
                generateData();
                return true;
            }
            if (id == R.id.action_explode) {
                explodeChart();
                return true;
            }
            if (id == R.id.action_center_circle) {
                hasCenterCircle = !hasCenterCircle;
                if (!hasCenterCircle) {
                    hasCenterText1 = false;
                    hasCenterText2 = false;
                }

                generateData();
                return true;
            }
            if (id == R.id.action_center_text1) {
                hasCenterText1 = !hasCenterText1;

                if (hasCenterText1) {
                    hasCenterCircle = true;
                }

                hasCenterText2 = false;

                generateData();
                return true;
            }
            if (id == R.id.action_center_text2) {
                hasCenterText2 = !hasCenterText2;

                if (hasCenterText2) {
                    hasCenterText1 = true;// text 2 need text 1 to by also drawn.
                    hasCenterCircle = true;
                }

                generateData();
                return true;
            }
            if (id == R.id.action_toggle_labels) {
                toggleLabels();
                return true;
            }
            if (id == R.id.action_toggle_labels_outside) {
                toggleLabelsOutside();
                return true;
            }
            if (id == R.id.action_animate) {
                prepareDataAnimation();
                mPieChart.startDataAnimation();
                return true;
            }
            if (id == R.id.action_toggle_selection_mode) {
                toggleLabelForSelected();
                Toast.makeText(getActivity(),
                        "Selection mode set to " + mPieChart.isValueSelectionEnabled() + " select any point.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }



        private class ValueTouchListener implements PieChartOnValueSelectListener {
            @Override
            public void onValueDeselected() {}

            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Toast.makeText(getActivity(), "Selected: " + sliceValue, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
