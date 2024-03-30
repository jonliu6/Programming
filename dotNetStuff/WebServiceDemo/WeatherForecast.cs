using System.Runtime.Serialization;

namespace WebServiceDemo
{
    [DataContract]
    public class WeatherForecast
    {
        [DataMember]
        public DateOnly Date { get; set; }

        [DataMember]
        public int TemperatureC { get; set; }

        [DataMember]
        public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);

        [DataMember]
        public string? Summary { get; set; }
    }
}
