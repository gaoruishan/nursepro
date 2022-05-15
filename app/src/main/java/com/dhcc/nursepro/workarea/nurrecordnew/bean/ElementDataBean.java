package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import com.base.commlibs.http.CommResult;

import java.util.ArrayList;
import java.util.List;

public class ElementDataBean extends CommResult {

    /**
     * Input : {"ElementBases":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"25","ElementType":"DropRadioElement","FormName":"DropRadioElement_25","GatherImportMapEffects":"","IsHide":"","NameText":"入院方式：","OprationItemList":[{"NumberValue":"1","Text":"步行","Value":"1"},{"NumberValue":"2","Text":"扶行","Value":"2"},{"NumberValue":"3","Text":"轮椅","Value":"3"},{"NumberValue":"4","Text":"平车","Value":"4"}],"ParentId":"","Required":"true","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"不合格","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"34","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"合格","OprationItemList":[{"NumberValue":"1","Text":"合格","Value":"1"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"35","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"不合格","OprationItemList":[{"NumberValue":"2","Text":"不合格","Value":"2"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item12"}],"ElementSets":[{"AllSatisfyFire":"false","FormName":"RadioElement_34","OnlySatisfyFire":"true","SetDataList":[{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Val":"1"},{"ChangeList":[{"Id":"29","Type":"Show,Enable"}],"FormName":"RadioElement_34","Val":"2"},{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Sign":"EqEmptyArray"}]}],"TemplateIndentity":"","statisticsList":[]}
     * Table : []
     */

    private DataBean data;
    /**
     * data : {"Input":{"ElementBases":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"25","ElementType":"DropRadioElement","FormName":"DropRadioElement_25","GatherImportMapEffects":"","IsHide":"","NameText":"入院方式：","OprationItemList":[{"NumberValue":"1","Text":"步行","Value":"1"},{"NumberValue":"2","Text":"扶行","Value":"2"},{"NumberValue":"3","Text":"轮椅","Value":"3"},{"NumberValue":"4","Text":"平车","Value":"4"}],"ParentId":"","Required":"true","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"不合格","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"34","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"合格","OprationItemList":[{"NumberValue":"1","Text":"合格","Value":"1"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"35","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"不合格","OprationItemList":[{"NumberValue":"2","Text":"不合格","Value":"2"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item12"}],"ElementSets":[{"AllSatisfyFire":"false","FormName":"RadioElement_34","OnlySatisfyFire":"true","SetDataList":[{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Val":"1"},{"ChangeList":[{"Id":"29","Type":"Show,Enable"}],"FormName":"RadioElement_34","Val":"2"},{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Sign":"EqEmptyArray"}]}],"TemplateIndentity":"","statisticsList":[]},"Table":[]}
     * firstIdList : [{"EmrCode":"DHCNURXHPGD","GuId":"","RecId":""},{"EmrCode":"DHCNURHZRYPGD","GuId":"16646E7EBFCC4D78B76E4E12A0A218B0","RecId":""},{"EmrCode":"DHCNURGRSZY1","GuId":"6EA04DDFE9464AEBB645F8697CC11DCD","RecId":""},{"EmrCode":"DHCNURXRYPGD","GuId":"DFAC3101B70445D48757596D7F26BD91","RecId":""},{"EmrCode":"DHCNURXRYPGD2","GuId":"11D5D27DFCFD46068033F4B0897E910E","RecId":""}]
     * msg :
     * status : 0
     */

    /**
     * EmrCode : DHCNURXHPGD
     * GuId :
     * RecId :
     */

    private List<FirstIdListBean> firstIdList;

    private List<StrictCodeListBean> StrictCodeList;
    /**
     * Content : item1-item2
     * DataType :
     * DescriptionItems : {"item1":"Item45","item2":"Item46"}
     * FormatFun :
     * Id : 2
     * Name : 出入平衡（新）
     * RepId : 12116
     */

    private List<FunListBean> funList;


    public DataBean getData() {
        return data!=null?data:new DataBean(); // EH 2022-04-26 后台json不再返回空类型，所有类型null判断
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<FirstIdListBean> getFirstIdList() {
        return firstIdList!=null?firstIdList:new ArrayList<FirstIdListBean>();
    }

    public void setFirstIdList(List<FirstIdListBean> firstIdList) {
        this.firstIdList = firstIdList;
    }

    public List<StrictCodeListBean> getStrictCodeList() {
        return StrictCodeList!=null?StrictCodeList:new ArrayList<StrictCodeListBean>();
    }

    public void setStrictCodeList(List<StrictCodeListBean> StrictCodeList) {
        this.StrictCodeList = StrictCodeList;
    }

    public List<FunListBean> getFunList() {
        return funList!=null?funList:new ArrayList<FunListBean>();
    }

    public void setFunList(List<FunListBean> funList) {
        this.funList = funList;
    }


    public static class DataBean {
        /**
         * ElementBases : [{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"25","ElementType":"DropRadioElement","FormName":"DropRadioElement_25","GatherImportMapEffects":"","IsHide":"","NameText":"入院方式：","OprationItemList":[{"NumberValue":"1","Text":"步行","Value":"1"},{"NumberValue":"2","Text":"扶行","Value":"2"},{"NumberValue":"3","Text":"轮椅","Value":"3"},{"NumberValue":"4","Text":"平车","Value":"4"}],"ParentId":"","Required":"true","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"不合格","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"34","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"合格","OprationItemList":[{"NumberValue":"1","Text":"合格","Value":"1"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"","DefaultValue":"","Disable":"","ElementId":"35","ElementType":"RadioElement","FormName":"RadioElement_34","GatherImportMapEffects":"","IsHide":"","NameText":"不合格","OprationItemList":[{"NumberValue":"2","Text":"不合格","Value":"2"}],"ParentId":"","Required":"true","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item12"}]
         * ElementSets : [{"AllSatisfyFire":"false","FormName":"RadioElement_34","OnlySatisfyFire":"true","SetDataList":[{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Val":"1"},{"ChangeList":[{"Id":"29","Type":"Show,Enable"}],"FormName":"RadioElement_34","Val":"2"},{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Sign":"EqEmptyArray"}]}]
         * TemplateIndentity :
         * statisticsList : []
         */

        private InputBean Input;
        private List<?> Table;

        public InputBean getInput() {
            return Input!=null?Input:new InputBean();
        }

        public void setInput(InputBean Input) {
            this.Input = Input;
        }

        public List<?> getTable() {
            return Table!=null?Table:new ArrayList<>();
        }

        public void setTable(List<?> Table) {
            this.Table = Table;
        }

        public static class InputBean {
            private String TemplateIndentity;
            /**
             * BindingTemplateID :
             * CallBackEffects :
             * CallBackReturnMapEffects :
             * DataSourceRef :
             * DataSourceRefInfo :
             * DefaultValue :
             * Disable :
             * ElementId : 25
             * ElementType : DropRadioElement
             * FormName : DropRadioElement_25
             * GatherImportMapEffects :
             * IsHide :
             * MEName: 出入平衡（新）
             * NameText : 入院方式：
             * OprationItemList : [{"NumberValue":"1","Text":"步行","Value":"1"},{"NumberValue":"2","Text":"扶行","Value":"2"},{"NumberValue":"3","Text":"轮椅","Value":"3"},{"NumberValue":"4","Text":"平车","Value":"4"}]
             * ParentId :
             * Required : true
             * SaveField : Item9
             * Signature :
             * SignatureAuto :
             * ToolTipText :
             */

            private List<ElementBasesBean> ElementBases;
            /**
             * AllSatisfyFire : false
             * FormName : RadioElement_34
             * OnlySatisfyFire : true
             * SetDataList : [{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Val":"1"},{"ChangeList":[{"Id":"29","Type":"Show,Enable"}],"FormName":"RadioElement_34","Val":"2"},{"ChangeList":[{"Id":"29","Type":"Hide,DisEnable"}],"FormName":"RadioElement_34","Sign":"EqEmptyArray"}]
             */

            private List<ElementSetsBean> ElementSets;
            private List<StatisticsListBean> statisticsList;

            public String getTemplateIndentity() {
                return TemplateIndentity!=null?TemplateIndentity:"";
            }

            public void setTemplateIndentity(String TemplateIndentity) {
                this.TemplateIndentity = TemplateIndentity;
            }

            public List<ElementBasesBean> getElementBases() {
                return ElementBases!=null?ElementBases:new ArrayList<ElementBasesBean>();
            }

            public void setElementBases(List<ElementBasesBean> ElementBases) {
                this.ElementBases = ElementBases;
            }

            public List<ElementSetsBean> getElementSets() {
                return ElementSets!=null?ElementSets:new ArrayList<ElementSetsBean>();
            }

            public void setElementSets(List<ElementSetsBean> ElementSets) {
                this.ElementSets = ElementSets;
            }

            public List<StatisticsListBean> getStatisticsList() {
                return statisticsList!=null?statisticsList:new ArrayList<StatisticsListBean>();
            }

            public void setStatisticsList(List<StatisticsListBean> statisticsList) {
                this.statisticsList = statisticsList;
            }

            public static class ElementBasesBean {
                private String BindingTemplateID;
                private String CallBackEffects;
                private String CallBackReturnMapEffects;
                private String DataSourceRef;
                private String DataSourceRefInfo;
                private String DefaultValue;
                private String Disable;
                private String DoctorAdviceRule;
                private String DoctorAdviceRuleLinkNo;
                private String ElementId;
                private String ElementType;
                private String FormName;
                private String FoundationJS;
                private String GatherEffects;
                private String GatherImportMapEffects;
                private String IsHide;
                private String MEName;
                private String MaxError;
                private String MaxWarning;
                private String MinError;
                private String MinWarning;
                private String NameText;
                private String ParentId;
                private String PointLen;
                private String Required;
                private String SaveField;
                private String Signature;
                private String SignatureAuto;
                private String ToolTipText;
                private String containerId;
                private String containerFlag;
                /**
                 * NumberValue : 1
                 * Text : 步行
                 * Value : 1
                 */

                private List<OprationItemListBean> OprationItemList;
                private List<RadioElementListBean> RadioElementList;

                public String getBindingTemplateID() {
                    return BindingTemplateID!=null?BindingTemplateID:"";
                }

                public void setBindingTemplateID(String BindingTemplateID) {
                    this.BindingTemplateID = BindingTemplateID;
                }

                public String getCallBackEffects() {
                    return CallBackEffects!=null?CallBackEffects:"";
                }

                public void setCallBackEffects(String CallBackEffects) {
                    this.CallBackEffects = CallBackEffects;
                }

                public String getCallBackReturnMapEffects() {
                    return CallBackReturnMapEffects!=null?CallBackReturnMapEffects:"";
                }

                public void setCallBackReturnMapEffects(String CallBackReturnMapEffects) {
                    this.CallBackReturnMapEffects = CallBackReturnMapEffects;
                }

                public String getDataSourceRef() {
                    return DataSourceRef!=null?DataSourceRef:"";
                }

                public void setDataSourceRef(String DataSourceRef) {
                    this.DataSourceRef = DataSourceRef;
                }

                public String getDataSourceRefInfo() {
                    return DataSourceRefInfo!=null?DataSourceRefInfo:"";
                }

                public void setDataSourceRefInfo(String DataSourceRefInfo) {
                    this.DataSourceRefInfo = DataSourceRefInfo;
                }

                public String getDefaultValue() {
                    return DefaultValue!=null?DefaultValue:"";
                }

                public void setDefaultValue(String DefaultValue) {
                    this.DefaultValue = DefaultValue;
                }

                public String getDisable() {
                    return Disable!=null?Disable:"";
                }

                public void setDisable(String Disable) {
                    this.Disable = Disable;
                }

                public String getDoctorAdviceRule() {
                    return DoctorAdviceRule!=null?DoctorAdviceRule:"";
                }

                public void setDoctorAdviceRule(String doctorAdviceRule) {
                    DoctorAdviceRule = doctorAdviceRule;
                }

                public String getDoctorAdviceRuleLinkNo() {
                    return DoctorAdviceRuleLinkNo!=null?DoctorAdviceRuleLinkNo:"";
                }

                public void setDoctorAdviceRuleLinkNo(String doctorAdviceRuleLinkNo) {
                    DoctorAdviceRuleLinkNo = doctorAdviceRuleLinkNo;
                }

                public String getElementId() {
                    return ElementId!=null?ElementId:"";
                }

                public void setElementId(String ElementId) {
                    this.ElementId = ElementId;
                }

                public String getElementType() {
                    return ElementType!=null?ElementType:"";
                }

                public void setElementType(String ElementType) {
                    this.ElementType = ElementType;
                }

                public String getFoundationJS() {
                    return FoundationJS!=null?FoundationJS:"";
                }

                public void setFoundationJS(String foundationJS) {
                    FoundationJS = foundationJS;
                }

                public String getFormName() {
                    return FormName!=null?FormName:"";
                }

                public void setFormName(String FormName) {
                    this.FormName = FormName;
                }

                public String getGatherEffects() {
                    return GatherEffects!=null?GatherEffects:"";
                }

                public void setGatherEffects(String gatherEffects) {
                    GatherEffects = gatherEffects;
                }

                public String getGatherImportMapEffects() {
                    return GatherImportMapEffects!=null?GatherImportMapEffects:"";
                }

                public void setGatherImportMapEffects(String GatherImportMapEffects) {
                    this.GatherImportMapEffects = GatherImportMapEffects;
                }

                public String getIsHide() {
                    return IsHide!=null?IsHide:"";
                }

                public void setIsHide(String IsHide) {
                    this.IsHide = IsHide;
                }

                public String getMEName() {
                    return MEName!=null?MEName:"";
                }

                public void setMEName(String MEName) {
                    this.MEName = MEName;
                }

                public String getMaxError() {
                    return MaxError!=null?MaxError:"";
                }

                public void setMaxError(String MaxError) {
                    this.MaxError = MaxError;
                }

                public String getMaxWarning() {
                    return MaxWarning!=null?MaxWarning:"";
                }

                public void setMaxWarning(String MaxWarning) {
                    this.MaxWarning = MaxWarning;
                }

                public String getMinError() {
                    return MinError!=null?MinError:"";
                }

                public void setMinError(String MinError) {
                    this.MinError = MinError;
                }

                public String getMinWarning() {
                    return MinWarning!=null?MinWarning:"";
                }

                public void setMinWarning(String MinWarning) {
                    this.MinWarning = MinWarning;
                }

                public String getNameText() {
                    return NameText!=null?NameText:"";
                }

                public void setNameText(String NameText) {
                    this.NameText = NameText;
                }

                public String getParentId() {
                    return ParentId!=null?ParentId:"";
                }

                public void setParentId(String ParentId) {
                    this.ParentId = ParentId;
                }

                public String getPointLen() {
                    return PointLen!=null?PointLen:"";
                }

                public void setPointLen(String PointLen) {
                    this.PointLen = PointLen;
                }

                public String getRequired() {
                    return Required!=null?Required:"";
                }

                public void setRequired(String Required) {
                    this.Required = Required;
                }

                public String getSaveField() {
                    return SaveField!=null?SaveField:"";
                }

                public void setSaveField(String SaveField) {
                    this.SaveField = SaveField;
                }

                public String getSignature() {
                    return Signature!=null?Signature:"";
                }

                public void setSignature(String Signature) {
                    this.Signature = Signature;
                }

                public String getSignatureAuto() {
                    return SignatureAuto!=null?SignatureAuto:"";
                }

                public void setSignatureAuto(String SignatureAuto) {
                    this.SignatureAuto = SignatureAuto;
                }

                public String getToolTipText() {
                    return ToolTipText!=null?ToolTipText:"";
                }

                public void setToolTipText(String ToolTipText) {
                    this.ToolTipText = ToolTipText;
                }

                public String getContainerId() {
                    return containerId!=null?containerId:"";
                }

                public void setContainerId(String containerId) {
                    this.containerId = containerId;
                }

                public String getContainerFlag() {
                    return containerFlag!=null?containerFlag:"";
                }

                public void setContainerFlag(String containerFlag) {
                    this.containerFlag = containerFlag;
                }

                public List<OprationItemListBean> getOprationItemList() {
                    return OprationItemList!=null?OprationItemList:new ArrayList<OprationItemListBean>();
                }

                public void setOprationItemList(List<OprationItemListBean> OprationItemList) {
                    this.OprationItemList = OprationItemList;
                }

                public List<RadioElementListBean> getRadioElementList() {
                    return RadioElementList!=null?RadioElementList:new ArrayList<RadioElementListBean>();
                }

                public void setRadioElementList(List<RadioElementListBean> RadioElementList) {
                    this.RadioElementList = RadioElementList;
                }

                public static class OprationItemListBean {
                    /**
                     * NumberValue : 1
                     * Text : 完全受限（1分）
                     * Value : 1
                     */

                    private String IsSelect;
                    private String NumberValue;
                    private String Text;
                    private String Value;

                    public String getIsSelect() {
                        return IsSelect!=null?IsSelect:"";
                    }

                    public void setIsSelect(String isSelect) {
                        IsSelect = isSelect;
                    }

                    public String getNumberValue() {
                        return NumberValue!=null?NumberValue:"";
                    }

                    public void setNumberValue(String NumberValue) {
                        this.NumberValue = NumberValue;
                    }

                    public String getText() {
                        return Text!=null?Text:"";
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }

                    public String getValue() {
                        return Value!=null?Value:"";
                    }

                    public void setValue(String Value) {
                        this.Value = Value;
                    }
                }

                public static class RadioElementListBean {
                    /**
                     * BindingTemplateID :
                     * CallBackEffects :
                     * CallBackReturnMapEffects :
                     * DataSourceRef :
                     * DefaultValue :
                     * Disable :
                     * ElementId : 44
                     * ElementType : RadioElement
                     * FormName : RadioElement_44
                     * IsHide :
                     * NameText : 急诊
                     * OprationItemList : [{"NumberValue":"1","Text":"急诊","Value":"1"}]
                     * ParentId : 40
                     * Required : true
                     * SaveField : Item7
                     * ToolTipText :
                     */

                    private String BindingTemplateID;
                    private String CallBackEffects;
                    private String CallBackReturnMapEffects;
                    private String DataSourceRef;
                    private String DefaultValue;
                    private String Disable;
                    private String ElementId;
                    private String ElementType;
                    private String FormName;
                    private String IsHide;
                    private String NameText;
                    private String ParentId;
                    private String IsSelect;
                    private String Required;
                    private String SaveField;
                    private String ToolTipText;
                    private String containerId;
                    private String containerFlag;
                    private List<OprationItemListBean> OprationItemList;

                    public String getBindingTemplateID() {
                        return BindingTemplateID!=null?BindingTemplateID:"";
                    }

                    public void setBindingTemplateID(String BindingTemplateID) {
                        this.BindingTemplateID = BindingTemplateID;
                    }

                    public String getCallBackEffects() {
                        return CallBackEffects!=null?CallBackEffects:"";
                    }

                    public void setCallBackEffects(String CallBackEffects) {
                        this.CallBackEffects = CallBackEffects;
                    }

                    public String getCallBackReturnMapEffects() {
                        return CallBackReturnMapEffects!=null?CallBackReturnMapEffects:"";
                    }

                    public void setCallBackReturnMapEffects(String CallBackReturnMapEffects) {
                        this.CallBackReturnMapEffects = CallBackReturnMapEffects;
                    }

                    public String getDataSourceRef() {
                        return DataSourceRef!=null?DataSourceRef:"";
                    }

                    public void setDataSourceRef(String DataSourceRef) {
                        this.DataSourceRef = DataSourceRef;
                    }

                    public String getDefaultValue() {
                        return DefaultValue!=null?DefaultValue:"";
                    }

                    public void setDefaultValue(String DefaultValue) {
                        this.DefaultValue = DefaultValue;
                    }

                    public String getDisable() {
                        return Disable!=null?Disable:"";
                    }

                    public void setDisable(String Disable) {
                        this.Disable = Disable;
                    }

                    public String getElementId() {
                        return ElementId!=null?ElementId:"";
                    }

                    public void setElementId(String ElementId) {
                        this.ElementId = ElementId;
                    }

                    public String getElementType() {
                        return ElementType!=null?ElementType:"";
                    }

                    public void setElementType(String ElementType) {
                        this.ElementType = ElementType;
                    }

                    public String getFormName() {
                        return FormName!=null?FormName:"";
                    }

                    public void setFormName(String FormName) {
                        this.FormName = FormName;
                    }

                    public String getIsHide() {
                        return IsHide!=null?IsHide:"";
                    }

                    public void setIsHide(String IsHide) {
                        this.IsHide = IsHide;
                    }

                    public String getNameText() {
                        return NameText!=null?NameText:"";
                    }

                    public void setNameText(String NameText) {
                        this.NameText = NameText;
                    }

                    public String getParentId() {
                        return ParentId!=null?ParentId:"";
                    }

                    public void setParentId(String ParentId) {
                        this.ParentId = ParentId;
                    }

                    public String getContainerId() {
                        return containerId!=null?containerId:"";
                    }

                    public void setContainerId(String containerId) {
                        this.containerId = containerId;
                    }

                    public String getContainerFlag() {
                        return containerFlag!=null?containerFlag:"";
                    }

                    public void setContainerFlag(String containerFlag) {
                        this.containerFlag = containerFlag;
                    }

                    public String getIsSelect() {
                        return IsSelect!=null?IsSelect:"";
                    }

                    public void setIsSelect(String isSelect) {
                        IsSelect = isSelect;
                    }

                    public String getRequired() {
                        return Required!=null?Required:"";
                    }

                    public void setRequired(String Required) {
                        this.Required = Required;
                    }

                    public String getSaveField() {
                        return SaveField!=null?SaveField:"";
                    }

                    public void setSaveField(String SaveField) {
                        this.SaveField = SaveField;
                    }

                    public String getToolTipText() {
                        return ToolTipText!=null?ToolTipText:"";
                    }

                    public void setToolTipText(String ToolTipText) {
                        this.ToolTipText = ToolTipText;
                    }

                    public List<OprationItemListBean> getOprationItemList() {
                        return OprationItemList!=null?OprationItemList:new ArrayList<OprationItemListBean>();
                    }

                    public void setOprationItemList(List<OprationItemListBean> OprationItemList) {
                        this.OprationItemList = OprationItemList;
                    }

                    public static class OprationItemListBean {
                        /**
                         * NumberValue : 1
                         * Text : 急诊
                         * Value : 1
                         */

                        private String NumberValue;
                        private String Text;
                        private String Value;

                        public String getNumberValue() {
                            return NumberValue!=null?NumberValue:"";
                        }

                        public void setNumberValue(String NumberValue) {
                            this.NumberValue = NumberValue;
                        }

                        public String getText() {
                            return Text!=null?Text:"";
                        }

                        public void setText(String Text) {
                            this.Text = Text;
                        }

                        public String getValue() {
                            return Value!=null?Value:"";
                        }

                        public void setValue(String Value) {
                            this.Value = Value;
                        }
                    }
                }
            }

            public static class ElementSetsBean {
                private String AllSatisfyFire;
                private String FormName;
                private String OnlySatisfyFire;
                /**
                 * ChangeList : [{"Id":"29","Type":"Hide,DisEnable"}]
                 * FormName : RadioElement_34
                 * Val : 1
                 */

                private List<SetDataListBean> SetDataList;

                public String getAllSatisfyFire() {
                    return AllSatisfyFire!=null?AllSatisfyFire:"";
                }

                public void setAllSatisfyFire(String AllSatisfyFire) {
                    this.AllSatisfyFire = AllSatisfyFire;
                }

                public String getFormName() {
                    return FormName!=null?FormName:"";
                }

                public void setFormName(String FormName) {
                    this.FormName = FormName;
                }

                public String getOnlySatisfyFire() {
                    return OnlySatisfyFire!=null?OnlySatisfyFire:"";
                }

                public void setOnlySatisfyFire(String OnlySatisfyFire) {
                    this.OnlySatisfyFire = OnlySatisfyFire;
                }

                public List<SetDataListBean> getSetDataList() {
                    return SetDataList!=null?SetDataList:new ArrayList<SetDataListBean>();
                }

                public void setSetDataList(List<SetDataListBean> SetDataList) {
                    this.SetDataList = SetDataList;
                }

                public static class SetDataListBean {
                    private String FormName;
                    private String Sign;
                    private String Val;
                    private String Val2;
                    /**
                     * Id : 29
                     * Type : Hide,DisEnable
                     */

                    private List<ChangeListBean> ChangeList;

                    public String getFormName() {
                        return FormName!=null?FormName:"";
                    }

                    public void setFormName(String FormName) {
                        this.FormName = FormName;
                    }

                    public String getSign() {
                        return Sign!=null?Sign:"";
                    }

                    public void setSign(String sign) {
                        Sign = sign;
                    }

                    public String getVal() {
                        return Val!=null?Val:"";
                    }

                    public void setVal(String Val) {
                        this.Val = Val;
                    }

                    public String getVal2() {
                        return Val2!=null?Val2:"";
                    }

                    public void setVal2(String val2) {
                        Val2 = val2;
                    }

                    public List<ChangeListBean> getChangeList() {
                        return ChangeList!=null?ChangeList:new ArrayList<ChangeListBean>();
                    }

                    public void setChangeList(List<ChangeListBean> ChangeList) {
                        this.ChangeList = ChangeList;
                    }

                    public static class ChangeListBean {
                        private String Id;
                        private String Items;
                        private String SelectItems;
                        private String Type;
                        private String Val;

                        public String getId() {
                            return Id!=null?Id:"";
                        }

                        public void setId(String Id) {
                            this.Id = Id;
                        }

                        public String getItems() {
                            return Items!=null?Items:"";
                        }

                        public void setItems(String Items) {
                            this.Items = Items;
                        }

                        public String getSelectItems() {
                            return SelectItems!=null?SelectItems:"";
                        }

                        public void setSelectItems(String selectItems) {
                            SelectItems = selectItems;
                        }

                        public String getType() {
                            return Type!=null?Type:"";
                        }

                        public void setType(String Type) {
                            this.Type = Type;
                        }

                        public String getVal() {
                            return Val!=null?Val:"";
                        }

                        public void setVal(String val) {
                            Val = val;
                        }
                    }
                }
            }

            public static class StatisticsListBean {
                /**
                 * CalType : Sum
                 * Effects : 77,79,81,83,85,87,89,91,93,95,97
                 * Id : 99
                 */

                private String CalType;
                private String Effects;
                private String Id;

                public String getCalType() {
                    return CalType!=null?CalType:"";
                }

                public void setCalType(String CalType) {
                    this.CalType = CalType;
                }

                public String getEffects() {
                    return Effects!=null?Effects:"";
                }

                public void setEffects(String Effects) {
                    this.Effects = Effects;
                }

                public String getId() {
                    return Id!=null?Id:"";
                }

                public void setId(String Id) {
                    this.Id = Id;
                }
            }
        }
    }

    public static class FirstIdListBean {
        private String EmrCode;
        private String GuId;
        private String RecId;

        public String getEmrCode() {
            return EmrCode!=null?EmrCode:"";
        }

        public void setEmrCode(String EmrCode) {
            this.EmrCode = EmrCode;
        }

        public String getGuId() {
            return GuId!=null?GuId:"";
        }

        public void setGuId(String GuId) {
            this.GuId = GuId;
        }

        public String getRecId() {
            return RecId!=null?RecId:"";
        }

        public void setRecId(String RecId) {
            this.RecId = RecId;
        }
    }

    public static class StrictCodeListBean {
        /**
         * Code : RadioElement_66
         */

        private List<InputsListBean> InputsList;
        /**
         * Code : 25
         */

        private List<RoseListBean> RoseList;

        public List<InputsListBean> getInputsList() {
            return InputsList!=null?InputsList:new ArrayList<InputsListBean>();
        }

        public void setInputsList(List<InputsListBean> InputsList) {
            this.InputsList = InputsList;
        }

        public List<RoseListBean> getRoseList() {
            return RoseList!=null?RoseList:new ArrayList<RoseListBean>();
        }

        public void setRoseList(List<RoseListBean> RoseList) {
            this.RoseList = RoseList;
        }

        public static class InputsListBean {
            private String Code;

            public String getCode() {
                return Code!=null?Code:"";
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                InputsListBean inputsListBean = (InputsListBean) obj;
                return Code.equals(inputsListBean.Code);
            }
        }

        public static class RoseListBean {
            private String Code;

            public String getCode() {
                return Code!=null?Code:"";
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                RoseListBean roseListBean = (RoseListBean) obj;
                return Code.equals(roseListBean.Code);

            }
        }
    }

    public static class FunListBean {
        private String Content;
        private String DataType;
        /**
         * item1 : Item45
         * item2 : Item46
         */

        private DescriptionItemsBean DescriptionItems;
        private String FormatFun;
        private String Id;
        private String Name;
        private String RepId;

        public String getContent() {
            return Content!=null?Content:"";
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getDataType() {
            return DataType!=null?DataType:"";
        }

        public void setDataType(String DataType) {
            this.DataType = DataType;
        }

        public DescriptionItemsBean getDescriptionItems() {
            return DescriptionItems;
        }

        public void setDescriptionItems(DescriptionItemsBean DescriptionItems) {
            this.DescriptionItems = DescriptionItems;
        }

        public String getFormatFun() {
            return FormatFun!=null?FormatFun:"";
        }

        public void setFormatFun(String FormatFun) {
            this.FormatFun = FormatFun;
        }

        public String getId() {
            return Id!=null?Id:"";
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name!=null?Name:"";
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getRepId() {
            return RepId!=null?RepId:"";
        }

        public void setRepId(String RepId) {
            this.RepId = RepId;
        }

        public static class DescriptionItemsBean {
            private String item1;
            private String item2;

            public String getItem1() {
                return item1!=null?item1:"";
            }

            public void setItem1(String item1) {
                this.item1 = item1;
            }

            public String getItem2() {
                return item2!=null?item2:"";
            }

            public void setItem2(String item2) {
                this.item2 = item2;
            }
        }
    }
}
