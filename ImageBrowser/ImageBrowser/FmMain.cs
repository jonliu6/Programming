using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ImageBrowser
{
    public partial class FmMain : Form
    {
        public FmMain()
        {
            InitializeComponent();
        }

        private Label createALabel(String lblTx, String lblTag, bool autoSize, Control parent, DockStyle ds)
        {
            Label lbl = new Label();
            lbl.Text = lblTx;
            lbl.Tag = lblTag;
            lbl.AutoSize = autoSize;
            lbl.Dock = ds;
            parent.Controls.Add(lbl);

            // lbl.Left = xPos;
            // lbl.Top = yPos;
            // lbl.MouseClick += new MouseEventHandler(showLabelTag);

            return lbl;
        }

        private void btnBrowser_Click(object sender, EventArgs e)
        {
            DialogResult dr = dlgOpenImages.ShowDialog();
            if (dr == DialogResult.OK)
            {
                int xPos = 0;
                int yPos = 0;
                int imgH = 96;
                int imgW = 96;
                int lenShort = 15;

                // clear the panel
                splitContainer1.Panel2.Controls.Clear();

                // load images into picture boxes
                foreach (string fname in dlgOpenImages.FileNames)
                {
                    try
                    {
                        String fn = fname.Substring(fname.LastIndexOf("\\") + 1);
                        if (fn.Length > lenShort)
                        {
                            fn = fn.Substring(0, lenShort) + "...";
                        }
                        PictureBox pbx = new PictureBox();
                        pbx.Size = new Size(imgW, imgH);
                        pbx.Location = new Point(xPos, yPos);

                        // get thumbnail of an image
                        Bitmap img = new Bitmap(fname);
                        pbx.Image = img.GetThumbnailImage(imgW, imgH, null, IntPtr.Zero); ;
                        xPos += imgW;
                        if ((xPos + imgW) > splitContainer1.Panel2.Width)
                        {
                            yPos += imgH;
                            xPos = 0;
                        }

                        // get filename labels
                        createALabel(fn, fname, false, pbx, DockStyle.Bottom);

                        splitContainer1.Panel2.Controls.Add(pbx);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error: " + ex.Message);
                    }
                    finally {
                    }

                }
            }
        }
    }
}
