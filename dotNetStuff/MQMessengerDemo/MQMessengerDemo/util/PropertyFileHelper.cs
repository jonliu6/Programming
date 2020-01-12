using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace MQMessengerDemo.util
{
    /// <summary>
    /// Read the key-value pairs of application configuration from a .properties file.
    /// </summary>
    public class PropertyFileHelper
    {
        private const String PROPERTY_DELIMITER = "=";
        private Dictionary<String, String> _properties = null;
         
        public PropertyFileHelper(String fileName)
        {
            LoadProperties(fileName);
        }

        public String GetProperty(String key)
        {
            if (!String.IsNullOrEmpty(key) && _properties != null && _properties.ContainsKey(key))
            {
                return _properties[key];
            }
            return null;
        }

        /// <summary>
        /// Load the key-value pairs of application configuration from the given (property) file into a Dictionary object.
        /// </summary>
        /// <param name="fileName"></param>
        public void LoadProperties(String fileName)
        {
            String[] lines = null;
            try
            {
                lines = File.ReadAllLines(fileName);
                if (lines != null)
                {
                    if (_properties == null)
                    {
                        _properties = new Dictionary<String, String>();
                    }
                    else
                    {
                        _properties.Clear();
                    }

                    for (int i = 0, len = lines.Length; i < len; ++i)
                    {
                        String ln = lines[i];
                        // ignore the comments and only look for key-value pairs
                        if (!String.IsNullOrEmpty(ln) && !ln.StartsWith("#") && !ln.StartsWith(";") && !ln.StartsWith("/") && !ln.StartsWith("'") && ln.Contains(PROPERTY_DELIMITER))
                        {
                            int delimiterIndex = ln.IndexOf(PROPERTY_DELIMITER);
                            String key = ln.Substring(0, delimiterIndex).Trim();
                            String value = ln.Substring(delimiterIndex + 1).Trim();
                            if ((value.StartsWith("\"") && value.EndsWith("\"")) || (value.StartsWith("\'") && value.EndsWith("\'")))
                            {
                                value = value.Substring(1, value.Length - 2);
                            }
                            _properties.Add(key, value);
                        }
                    }
                }
            }
            catch (Exception ex) {
                Console.WriteLine("ERROR caught when loading the properties from " + fileName + "\n" + ex.Message);
            }
        }

        /// <summary>
        /// Printout for debugging/testing purpose
        /// </summary>
        /// <returns></returns>
        public String DisplayAllProperties()
        {
            if (_properties != null && _properties.Count > 0)
            {
                StringBuilder sb = new StringBuilder();
                foreach(KeyValuePair<String, String> prop in _properties)
                {
                    sb.AppendLine(prop.Key + PROPERTY_DELIMITER + prop.Value);
                }
                return sb.ToString();
            }
            else
            {
                return "No properties found!";
            }
        }
    }
}
