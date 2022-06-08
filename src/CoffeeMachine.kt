object CoffeeMachine {

    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550

    fun start() {
        println("Welcome!")
        println()
        println(
            """
               |Please select an action:
               |buy - buy a coffee
               |fill - add water, milk, cups, coffee beans
               |take - collect the money
               |status - print status of resources
            """.trimMargin()
        )

    }

    private fun buy() {
        TODO()
    }

    private fun fill() {
        TODO()
    }

    private fun take() {
        TODO()
    }

    private fun status() {
        println(
            "The coffee machine has:\n" +
                    "$water ml of water\n" +
                    "$milk ml of milk\n" +
                    "$beans grams of coffee beans\n" +
                    "$cups of disposable cups\n" +
                    "$money$"
        )
    }
}
