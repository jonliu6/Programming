﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:2.0.50727.8806
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WSDemo.ServiceReference1 {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://demo.freecode.org/schemas", ConfigurationName="ServiceReference1.NotePort")]
    public interface NotePort {
        
        // CODEGEN: Generating message contract since the operation getNoteByTitle is neither RPC nor document wrapped.
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute()]
        WSDemo.ServiceReference1.getNoteByTitleResponse1 getNoteByTitle(WSDemo.ServiceReference1.getNoteByTitleRequest1 request);
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "2.0.50727.8773")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://demo.freecode.org/schemas")]
    public partial class getNoteByTitleRequest : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string titleField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=0)]
        public string title {
            get {
                return this.titleField;
            }
            set {
                this.titleField = value;
                this.RaisePropertyChanged("title");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "2.0.50727.8773")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://demo.freecode.org/schemas")]
    public partial class note : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string titleField;
        
        private string categoryField;
        
        private string subCategoryField;
        
        private string descriptionField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=0)]
        public string title {
            get {
                return this.titleField;
            }
            set {
                this.titleField = value;
                this.RaisePropertyChanged("title");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=1)]
        public string category {
            get {
                return this.categoryField;
            }
            set {
                this.categoryField = value;
                this.RaisePropertyChanged("category");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=2)]
        public string subCategory {
            get {
                return this.subCategoryField;
            }
            set {
                this.subCategoryField = value;
                this.RaisePropertyChanged("subCategory");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=3)]
        public string description {
            get {
                return this.descriptionField;
            }
            set {
                this.descriptionField = value;
                this.RaisePropertyChanged("description");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "2.0.50727.8773")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://demo.freecode.org/schemas")]
    public partial class getNoteByTitleResponse : object, System.ComponentModel.INotifyPropertyChanged {
        
        private note noteField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=0)]
        public note note {
            get {
                return this.noteField;
            }
            set {
                this.noteField = value;
                this.RaisePropertyChanged("note");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class getNoteByTitleRequest1 {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://demo.freecode.org/schemas", Order=0)]
        public WSDemo.ServiceReference1.getNoteByTitleRequest getNoteByTitleRequest;
        
        public getNoteByTitleRequest1() {
        }
        
        public getNoteByTitleRequest1(WSDemo.ServiceReference1.getNoteByTitleRequest getNoteByTitleRequest) {
            this.getNoteByTitleRequest = getNoteByTitleRequest;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class getNoteByTitleResponse1 {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://demo.freecode.org/schemas", Order=0)]
        public WSDemo.ServiceReference1.getNoteByTitleResponse getNoteByTitleResponse;
        
        public getNoteByTitleResponse1() {
        }
        
        public getNoteByTitleResponse1(WSDemo.ServiceReference1.getNoteByTitleResponse getNoteByTitleResponse) {
            this.getNoteByTitleResponse = getNoteByTitleResponse;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public interface NotePortChannel : WSDemo.ServiceReference1.NotePort, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public partial class NotePortClient : System.ServiceModel.ClientBase<WSDemo.ServiceReference1.NotePort>, WSDemo.ServiceReference1.NotePort {
        
        public NotePortClient() {
        }
        
        public NotePortClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public NotePortClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public NotePortClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public NotePortClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        WSDemo.ServiceReference1.getNoteByTitleResponse1 WSDemo.ServiceReference1.NotePort.getNoteByTitle(WSDemo.ServiceReference1.getNoteByTitleRequest1 request) {
            return base.Channel.getNoteByTitle(request);
        }
        
        public WSDemo.ServiceReference1.getNoteByTitleResponse getNoteByTitle(WSDemo.ServiceReference1.getNoteByTitleRequest getNoteByTitleRequest) {
            WSDemo.ServiceReference1.getNoteByTitleRequest1 inValue = new WSDemo.ServiceReference1.getNoteByTitleRequest1();
            inValue.getNoteByTitleRequest = getNoteByTitleRequest;
            WSDemo.ServiceReference1.getNoteByTitleResponse1 retVal = ((WSDemo.ServiceReference1.NotePort)(this)).getNoteByTitle(inValue);
            return retVal.getNoteByTitleResponse;
        }
    }
}