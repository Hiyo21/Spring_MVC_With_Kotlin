package mvc.domain

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "RestfulSampleVO")
data class RestfulSampleVO(val name:String, val address:String) {
 constructor() : this(name = "", address = "")
}
