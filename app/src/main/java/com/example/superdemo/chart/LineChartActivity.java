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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.superdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * LineChartActivity
 */
public class LineChartActivity extends AppCompatActivity {
    @BindView(R.id.container)
    FrameLayout container;

    private PlaceholderFragment mFragment = new PlaceholderFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_line_chart);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, mFragment).commit();
        }

    }

    public static class PlaceholderFragment extends Fragment {

        @BindView(R.id.chart)
        LineChartView mLinechart;
        @BindView(R.id.toolbar)
        Toolbar mToolbar;
        AppCompatActivity compatActivity;

        private LineChartData data;
        private int numberOfLines = 1;
        private int maxNumberOfLines = 4;
        private int numberOfPoints = 12;

        float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

        private boolean hasAxes = true;
        private boolean hasAxesNames = true;
        private boolean hasLines = true;
        private boolean hasPoints = true;
        private ValueShape shape = ValueShape.CIRCLE;
        private boolean isFilled = false;
        private boolean hasLabels = false;
        private boolean isCubic = false;
        private boolean hasLabelForSelected = false;
        private boolean pointsHaveDifferentColor;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

            ButterKnife.bind(this, rootView);
            compatActivity = (AppCompatActivity) getActivity();
            compatActivity.setSupportActionBar(mToolbar);
            setHasOptionsMenu(true);

            mLinechart.setOnValueTouchListener(new ValueTouchListener());

            // Generate some random values.
            generateValues();

            generateData();

            // Disable viewport recalculations, see toggleCubic() method for more info.
            mLinechart.setViewportCalculationEnabled(false);

            resetViewport();

            return rootView;
        }


        private void generateValues() {
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfPoints; ++j) {
                    randomNumbersTab[i][j] = (float) Math.random() * 100f;
                }
            }
        }

        //line data
        private void generateData() {
            List<Line> lists = new ArrayList<>();

            for (int i = 0; i < maxNumberOfLines; i++) {
                List<PointValue> values = new ArrayList<>();
                for (int j = 0; j < numberOfPoints; ++j) {
                    values.add(new PointValue(j, randomNumbersTab[i][j]));
                }

                Line line = new Line(values);
                line.setColor(ChartUtils.COLORS[i]);
                line.setShape(shape);
                line.setCubic(isCubic);
                line.setFilled(isFilled);
                line.setHasLabels(hasLabels);
                line.setHasLabelsOnlyForSelected(hasLabelForSelected);
                line.setHasLines(hasLines);
                line.setHasPoints(hasPoints);

                if (pointsHaveDifferentColor) {
                    line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
                }
                lists.add(line);
            }

            data = new LineChartData(lists);

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

            data.setBaseValue(Float.NEGATIVE_INFINITY);
            mLinechart.setLineChartData(data);

        }

        private void resetViewport() {
            // Reset viewport height range to (0,100)
            final Viewport v = new Viewport(mLinechart.getMaximumViewport());
            v.bottom = 0;
            v.top = 100;
            v.left = 0;
            v.right = numberOfPoints - 1;
            mLinechart.setMaximumViewport(v);
            mLinechart.setCurrentViewport(v);
        }

        private void reset() {
            numberOfLines = 1;

            hasAxes = true;
            hasAxesNames = true;
            hasLines = true;
            hasPoints = true;
            shape = ValueShape.CIRCLE;
            isFilled = false;
            hasLabels = false;
            isCubic = false;
            hasLabelForSelected = false;
            pointsHaveDifferentColor = false;

            mLinechart.setValueSelectionEnabled(hasLabelForSelected);
            resetViewport();
        }

        /**
         * Adds lines to data, after that data should be set again with
         * {@link LineChartView#setLineChartData(LineChartData)}. Last 4th line has non-monotonically x values.
         */
        private void addLineToData() {
            if (data.getLines().size() >= maxNumberOfLines) {
                Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                ++numberOfLines;
            }

            generateData();
        }

        private void toggleLines() {
            hasLines = !hasLines;
            generateData();
        }

        private void togglePoints() {
            hasPoints = !hasPoints;
            generateData();
        }

        private void toggleCubic() {
            isCubic = !isCubic;
            generateData();

            if (isCubic) {
                // It is good idea to manually set a little higher max viewport for cubic lines because sometimes line
                // go above or below max/min. To do that use Viewport.inest() method and pass negative value as dy
                // parameter or just set top and bottom values manually.
                // In this example I know that Y values are within (0,100) range so I set viewport height range manually
                // to (-5, 105).
                // To make this works during animations you should use Chart.setViewportCalculationEnabled(false) before
                // modifying viewport.
                // Remember to set viewport after you call setLineChartData().
                final Viewport v = new Viewport(mLinechart.getMaximumViewport());
                v.bottom = -5;
                v.top = 105;
                // You have to set max and current viewports separately.
                mLinechart.setMaximumViewport(v);
                // I changing current viewport with animation in this case.
                mLinechart.setCurrentViewportWithAnimation(v);
            } else {
                // If not cubic restore viewport to (0,100) range.
                final Viewport v = new Viewport(mLinechart.getMaximumViewport());
                v.bottom = 0;
                v.top = 100;

                // You have to set max and current viewports separately.
                // In this case, if I want animation I have to set current viewport first and use animation listener.
                // Max viewport will be set in onAnimationFinished method.
                mLinechart.setViewportAnimationListener(new ChartAnimationListener() {
                    @Override
                    public void onAnimationStarted() {

                    }

                    @Override
                    public void onAnimationFinished() {
                        // Set max viewpirt and remove listener.
                        mLinechart.setMaximumViewport(v);
                        mLinechart.setViewportAnimationListener(null);
                    }
                });

                mLinechart.setCurrentViewportWithAnimation(v);
            }
        }

        private void toggleFilled() {
            isFilled = !isFilled;
            generateData();
        }

        private void togglePointColor() {
            pointsHaveDifferentColor = !pointsHaveDifferentColor;
            generateData();
        }

        private void setCircles() {
            shape = ValueShape.CIRCLE;
            generateData();
        }

        private void setSquares() {
            shape = ValueShape.SQUARE;

            generateData();
        }

        private void setDiamonds() {
            shape = ValueShape.DIAMOND;

            generateData();
        }

        private void toggleLabels() {
            hasLabels = !hasLabels;

            if (hasLabels) {
                hasLabelForSelected = false;
                mLinechart.setValueSelectionEnabled(hasLabelForSelected);
            }
            generateData();
        }

        private void toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected;

            mLinechart.setValueSelectionEnabled(hasLabelForSelected);

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
         * method(don't confuse with View.animate()). If you operate on data that was set before you don't have to call
         * {@link LineChartView#setLineChartData(LineChartData)} again.
         */
        private void prepareDataAnimation() {
            for (Line line : data.getLines()) {
                for (PointValue value : line.getValues()) {
                    // Here I modify target only for Y values but it is OK to modify X targets as well.
                    value.setTarget(value.getX(), (float) Math.random() * 100);
                }
            }
        }

        private class ValueTouchListener implements LineChartOnValueSelectListener {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue pointValue) {
                Toast.makeText(getActivity(), "Selected: " + pointValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.line_chart, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_reset:
                    generateValues();
                    reset();
                    generateData();
                    break;
                case R.id.action_add_line:
                    addLineToData();
                    break;
                case R.id.action_toggle_lines:
                    //隐藏线
                    toggleLines();
                    break;
                case R.id.action_toggle_points:
                    //隐藏点
                    togglePoints();
                    break;
                case R.id.action_toggle_cubic:
                    //改变连线状态
                    toggleCubic();
                    break;
                case R.id.action_toggle_area:
                    //填充颜色
                    toggleFilled();
                    break;
                case R.id.action_point_color:
                    //改变圆点颜色
                    togglePointColor();
                    break;
                case R.id.action_shape_circles:
                    //设置圆点形状--圆形
                    setCircles();
                    break;
                case R.id.action_shape_square:
                    //设置圆点形状--方形
                    setSquares();
                    break;
                case R.id.action_shape_diamond:
                    //设置圆点形状--菱形
                    setDiamonds();
                    break;
                case R.id.action_toggle_labels:
                    //显示每个点的值(Labels)
                    toggleLabels();
                    break;
                case R.id.action_toggle_axes:
                    //显示隐藏Ax
                    toggleAxes();
                    break;
                case R.id.action_toggle_axes_names:
                    //设置xy轴的Ax name显示隐藏
                    toggleAxesNames();
                    break;

                case R.id.action_animate:
                    //动态变更数据
                    prepareDataAnimation();
                    mLinechart.startDataAnimation();
                    break;

                case R.id.action_toggle_selection_mode:
                    //是否保持选中点的状态
                    toggleLabelForSelected();

                    Toast.makeText(getActivity(),
                            "Selection mode set to " + mLinechart.isValueSelectionEnabled() + " select any point.",
                            Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_toggle_touch_zoom:
                    //设置是否可以放大缩小
                    mLinechart.setZoomEnabled(!mLinechart.isZoomEnabled());
                    Toast.makeText(getActivity(), "IsZoomEnabled " + mLinechart.isZoomEnabled(), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_zoom_both:
                    //设置两个方向都可以升缩
                    mLinechart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
                    break;

                case R.id.action_zoom_horizontal:
                    //设置水平升缩
                    mLinechart.setZoomType(ZoomType.HORIZONTAL);
                    break;

                case R.id.action_zoom_vertical:
                    //设置垂直升缩
                    mLinechart.setZoomType(ZoomType.VERTICAL);
                    break;
            }
            return true;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.line_chart, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.action_reset:
//                mFragment.reset();
//                mFragment.generateData();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
