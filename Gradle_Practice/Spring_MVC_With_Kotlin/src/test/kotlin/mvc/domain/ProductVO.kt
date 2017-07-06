package mvc.domain

open class ProductVO(var name: String, var price: Double) {

    override fun toString(): String {
        return "ProductVO [name = $name, price = $price]"
    }
}