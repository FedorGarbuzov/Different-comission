import kotlin.math.roundToInt

fun main() {
    val payMaxDay = 150_000
    val payMaxMounth = 600_000
    val vkPayMaxDay = 15_000
    val vkPayMaxMounth = 40_000

    val payDay = 110_000
    val vkPayDay = 11_000

    pay("Visa", 30_000, 3_840, payMaxDay, payMaxMounth, vkPayMaxDay, vkPayMaxMounth, payDay, vkPayDay)
}

fun pay(
    card: String = "VkPay",
    lastAmount: Int = 0,
    thisAmount: Int,
    payMaxDay: Int,
    payMaxMounth: Int,
    vkPayMaxDay: Int,
    vkPayMaxMounth: Int,
    payDay: Int,
    vkPayDay: Int,
) {
    if ((lastAmount + thisAmount) < payMaxMounth && (payDay + thisAmount) < payMaxDay) {
        when (card) {
            "Mastercard", "Maestro" -> calculateMasterCardorMaestro(lastAmount, thisAmount)
            "Visa", "МИР" -> calculateVisaOrMir(thisAmount)
            else -> calculateVkPay(vkPayDay, thisAmount, vkPayMaxDay, lastAmount, vkPayMaxMounth)
        }
    } else {
        println("Превышен лимит!")
    }
}

private fun calculateVkPay(
    vkPayDay: Int,
    thisAmount: Int,
    vkPayMaxDay: Int,
    lastAmount: Int,
    vkPayMaxMounth: Int
) {
    if ((vkPayDay + thisAmount) < vkPayMaxDay && (lastAmount + thisAmount) < vkPayMaxMounth) {
        println("Перевод в сумме: $thisAmount коп. комиссия: 0 коп.")
    } else {
        println("Превышен лимит!")
    }
}

private fun calculateVisaOrMir(thisAmount: Int) {
    if ((thisAmount / 100 * 0.75) < 3500) {
        println("Перевод в сумме: $thisAmount коп. комиссия: 3500 коп.")
    } else {
        println("Перевод в сумме: $thisAmount коп. комиссия: ${(thisAmount / 100 * 0.75).roundToInt()} коп.")
    }
}

private fun calculateMasterCardorMaestro(lastAmount: Int, thisAmount: Int) {
    if (lastAmount < 75_000) {
        println("Перевод в сумме: $thisAmount коп. комиссия: 0 коп.")
    } else {
        println("Перевод в сумме: $thisAmount коп. комиссия: ${((thisAmount / 100 * 0.6) + 2000).roundToInt()} коп.")
    }
}
