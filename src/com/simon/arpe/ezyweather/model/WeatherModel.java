package com.simon.arpe.ezyweather.model;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.simon.arpe.ezyweather.R;
import com.simon.arpe.ezyweather.net.ConnectionHelper;
import com.simon.arpe.ezyweather.otto.BusProvider;
import com.simon.arpe.ezyweather.otto.weather.WeatherEvent;

public class WeatherModel {

	private static final String TAG = WeatherModel.class.getSimpleName();

	private static final String HTTP = "http";

	private final Context context;
	private String weatherReport;

	public WeatherModel(Context context) {
		this.context = context;
	}

	public String getWeatherReport() {
		return weatherReport;
	}

	public void checkLondon() {
		String endPoint = context.getString(R.string.end_point);
		String apiById = context.getString(R.string.get_weather_by_id, context.getString(R.string.london_id));

		URL url = null;
		try {
			url = new URL(HTTP, endPoint, apiById);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (url != null) {
			new CheckLondonTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
		}
	}

	private static class CheckLondonTask extends AsyncTask<URL, Void, Void> {
		private final WeatherModel model;

		public CheckLondonTask(WeatherModel model) {
			this.model = model;
		}

		@Override
		protected Void doInBackground(URL... params) {
			model.weatherReport = ConnectionHelper.conntect(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			Log.d(TAG, "connectionOK");
			BusProvider.getInstance().post(new WeatherEvent(model.weatherReport));

		}

	}

}
