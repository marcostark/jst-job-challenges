package br.com.marcosouza.justamobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.WeatherResponse;
import br.com.marcosouza.justamobile.ui.viewmodels.WeatherViewModel;
import br.com.marcosouza.justamobile.util.DateUtils;

public class WeatherFragment extends Fragment {

    private ImageView mImageViewIconForecast;
    private TextView mTextViewName;
    private TextView mTextViewHumidity;
    private TextView mTextViewMain;
    private TextView mTextViewTemp;
    private TextView mTextViewTempMax;
    private TextView mTextViewTempMin;
    private TextView mTextViewSunset;
    private TextView mTextViewSunrise;

    private WeatherViewModel weatherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weatherViewModel =
                ViewModelProviders.of(this).get(WeatherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_weather, container, false);

        mImageViewIconForecast = root.findViewById(R.id.image_view_icon);
        mTextViewTemp = root.findViewById(R.id.text_view_temp);
        mTextViewTempMin = root.findViewById(R.id.text_view_temp_min);
        mTextViewTempMax = root.findViewById(R.id.text_view_temp_max);
        mTextViewHumidity = root.findViewById(R.id.text_view_humidty);
        mTextViewSunrise = root.findViewById(R.id.text_view_sunrise);
        mTextViewSunset = root.findViewById(R.id.text_view_sunset);
        mTextViewMain = root.findViewById(R.id.text_view_main);
        mTextViewName = root.findViewById(R.id.text_view_city);

        weatherViewModel.init();
        weatherViewModel.getWeatherRepository().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                loadComponents(weatherResponse);
            }
        });
        return root;
    }

    void loadComponents(WeatherResponse weatherResponse){

        mTextViewTemp.setText(String.format("%s °", weatherResponse.getTemp()));
        mTextViewTempMin.setText(String.format("%s °", weatherResponse.getTempMin()));
        mTextViewTempMax.setText(String.format("%s °", weatherResponse.getTempMax()));
        mTextViewHumidity.setText(String.format("%s %%", weatherResponse.getHumidity()));
        mTextViewSunrise.setText(String.valueOf(DateUtils.convertDate(weatherResponse.getSunrise())));
        mTextViewSunset.setText(String.valueOf(DateUtils.convertDate(weatherResponse.getSunset())));
        mTextViewMain.setText(weatherResponse.getMain());
        mTextViewName.setText(String.format("%s - %s",weatherResponse.getName(), weatherResponse.getCountry() ));

        Picasso.get().load("http://openweathermap.org/img/w/" + weatherResponse.getIcon() + ".png").into(mImageViewIconForecast);

    }
}