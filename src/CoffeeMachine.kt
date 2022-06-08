object CoffeeMachine {

    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    private var isPowerOn = true

    private val actions = mapOf(
        "buy" to ::buy, "fill" to ::fill, "take" to ::take, "status" to ::status, "exit" to ::exit
    )

    fun start() {
        println("Welcome!\n")

        while (isPowerOn) {
            printActions()
            actions[readAction()]?.invoke()
        }
    }

    private fun buy() {
        val msg = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 0 - back to main menu: "
        val error = "Error! Please enter a number from 0 to 3"

        while (true) {

            when (inputNumber(msg, error, 3)) {
                1 -> makeCoffee(Coffee.ESPRESSO)
                2 -> makeCoffee(Coffee.LATTE)
                3 -> makeCoffee(Coffee.CAPPUCCINO)
                0 -> break
            }
            return
        }
    }

    private fun makeCoffee(coffee: Coffee) {
        val notEnough = "Sorry, not enough"

        if (water < coffee.water) println("$notEnough water!")
        else if (milk < coffee.milk) println("$notEnough milk!")
        else if (beans < coffee.grams) println("$notEnough coffee beans!")
        else if (cups < 1) println("$notEnough cups!")
        else {
            water -= coffee.water
            milk -= coffee.milk
            beans -= coffee.grams
            cups -= 1
            money += coffee.price
            println("Making your ${coffee.name.lowercase()}\n")
        }
    }

    private fun fill() {
        val msg1 = "Write how many"
        val msg2 = "do you want to add: "
        val error = "Error! Please enter a positive number from 0 to 1000(water), 500(milk), 200(coffee), 20(cups)."

        water += inputNumber("$msg1 ml of water $msg2", error, 1000)
        milk += inputNumber("$msg1 ml of milk $msg2", error, 500)
        beans += inputNumber("$msg1 grams of coffee beans $msg2", error, 200)
        cups += inputNumber("$msg1 disposable cups of coffee $msg2", error, 20)
    }

    private fun take() {
        println("I gave you $money$\n")
        money = 0
    }

    private fun status() {
        println(
            """
                |The coffee machine has:
                |$water ml of water
                |$milk ml of milk
                |$beans grams of coffee beans
                |$cups of disposable cups
                |$money$
            """.trimMargin() + "\n"
        )
    }

    private fun exit() {
        isPowerOn = false
    }

    private fun printActions() {
        println(
            """
               |Please select an action:
               |buy - buy a coffee
               |add - add water, milk, cups, coffee beans
               |take - collect the money
               |status - print status of resources
               |exit - power off
            """.trimMargin() + "\n"
        )
    }

    /**
     * Read and validate action from user input
     */
    private fun readAction(): String {
        while (true) {
            val input = readln()
            if (actions.containsKey(input))
                return input
            else
                println("Error! Try again!")
        }
    }

    /**
     * A helper method for validation input
     */
    private fun inputNumber(
        printMsg: String,
        errorMsg: String,
        maxNum: Int = Int.MAX_VALUE,
    ): Int {
        while (true) {
            println(printMsg)

            try {
                val input = readln().toInt()

                if (input in 0..maxNum) return input
                else println(errorMsg)

            } catch (e: Exception) {
                println(errorMsg)
            }
        }
    }
}
