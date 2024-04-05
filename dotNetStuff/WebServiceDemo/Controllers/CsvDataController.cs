using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Text;

namespace WebServiceDemo.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CsvDataController : ControllerBase
    {
        private const string MimeType = "text/csv";
        private const string FileName = "test.csv";
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<CsvDataController> _logger;

        public CsvDataController(ILogger<CsvDataController> logger)
        {
            _logger = logger;
        }

        /// <summary>
        /// reference: https://code-maze.com/aspnetcore-web-api-return-file/
        /// </summary>
        /// <returns></returns>
        [HttpGet(Name = "GetCsvContent")]
        public IActionResult Get()
        {
            IEnumerable<WeatherForecast> forecastValues = Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
                TemperatureC = Random.Shared.Next(-20, 55),
                Summary = Summaries[Random.Shared.Next(Summaries.Length)]
            })
            .ToArray();

            StringBuilder sb = new StringBuilder();
            //sb.Append("Yes,\"BCSI SharePoint, FileNet Location, J Drive etc\"").Append(Environment.NewLine);
            //sb.Append("No,\"\"");

            foreach (WeatherForecast forecastRecord in forecastValues)
            {
                sb.Append(forecastRecord.Date).Append(",");
                sb.Append(forecastRecord.TemperatureC).Append(",");
                sb.Append(forecastRecord.Summary);
                sb.AppendLine();
            }
            byte[] byteArray = Encoding.ASCII.GetBytes(sb.ToString());

            return File(byteArray, MimeType, FileName);
        }

    }
}
