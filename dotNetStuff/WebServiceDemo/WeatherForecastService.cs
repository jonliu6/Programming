using System.ServiceModel;

namespace WebServiceDemo
{
    [ServiceContract]
    public interface IWeatherForecastService
    {
        [OperationContract]
        int convertCelsiusToFahrenheit(int celsiusValue);
    }

    public class WeatherForecastService : IWeatherForecastService
    {
        public int convertCelsiusToFahrenheit(int celsiusValue)
        {
            return 32 + (int)(celsiusValue / 0.5556);
        }
    }
}
