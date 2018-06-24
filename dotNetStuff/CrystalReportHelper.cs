using CrystalDecisions.CrystalReports.Engine;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;

namespace RemoteDispatchTestClient.util
{
    class CrystalReportHelper
    {
        private ReportDocument _report = null;
        public ReportDocument Report
        {
            get { return _report; }
            set { _report = value; }
        }

        public CrystalReportHelper(String reportFileName)
        {
            _report = new ReportDocument();
            _report.Load(reportFileName);
        }

        public CrystalReportHelper(String reportFileName, DataTable dataSrc)
        {
            _report = new ReportDocument();
            _report.Load(reportFileName);
            _report.SetDataSource(dataSrc);
        }


    }
}
