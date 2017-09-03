using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Security.Cryptography;

namespace ChecksumHelper
{
    class ChecksumHelper
    {
        // deprecated since string type is enough
        public enum HashType : int
        {
            MD5, SHA1, SHA256, SHA512
        }

        // return a proper algorithm based on the give hash algorithm code
        public HashAlgorithm getHashAlgorithm(string hashTypeText)
        {
            HashAlgorithm algorithm = null;

            switch (hashTypeText)
            {
                case "MD5":
                    // aHashType = HashType.MD5;
                    algorithm = new MD5CryptoServiceProvider();
                    break;
                case "SHA1":
                    // aHashType = HashType.SHA1;
                    algorithm = new SHA1CryptoServiceProvider();
                    break;
                case "SHA256":
                    // aHashType = HashType.SHA256;
                    algorithm = new SHA256CryptoServiceProvider();
                    break;
                case "SHA512":
                    // aHashType = HashType.SHA512;
                    algorithm = new SHA512CryptoServiceProvider();
                    break;
                default:
                    break;
            }

            return algorithm;
        }

        public string getHash(string originalText, string hashTypeText)
        {
            string hashString = "";
            UnicodeEncoding UE = new UnicodeEncoding(); // Unicode 16 Little Endian bytes
            byte[] hashValue;
            byte[] message = UE.GetBytes(originalText);
            // byte[] message = Encoding.ASCII.GetBytes(originalText); // Plant Text bytes
            HashAlgorithm algorithm = null;
            // string hex = "";
            // HashType aHashType;

            algorithm = getHashAlgorithm(hashTypeText);

            if (algorithm == null)
            {
                hashString = "Invalid Hash Type.";
            }
            else
            {
                hashValue = algorithm.ComputeHash(message);
                //Option 1
                //StringBuilder formatted = new StringBuilder(hashValue.Length); 
                //foreach (byte x in hashValue)
                //{
                //    //hashString += String.Format("{0:x2}", x);
                //    formatted.AppendFormat("{0:x2}", x);
                //}
                //hashString = formatted.ToString();

                //Option 2
                hashString = BitConverter.ToString(hashValue).Replace("-", "").ToLower();
            }

            return hashString;
        }

        public string getFileHash(FileStream fileStream, string hashTypeText)
        {
            string strResult = "";
            string strHashData = "";
            HashAlgorithm algorithm = getHashAlgorithm(hashTypeText);

            byte[] arrbytHashValue;
            
            try
            {
                if (algorithm != null && fileStream != null)
                {
                    arrbytHashValue = algorithm.ComputeHash(fileStream);
                    fileStream.Close();

                    strHashData = BitConverter.ToString(arrbytHashValue);
                    strHashData = strHashData.Replace("-", "");
                    strResult = strHashData;
                }
                else
                {
                    MessageBox.Show("Please check your algorithm choice and file location.", "getFileHash Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show( ex.Message, "getFileHash Error", MessageBoxButtons.OK, MessageBoxIcon.Error );
            }

            return (strResult.ToLower());
        }
    }
}
