class ImprovedPilot {

    var depth: Int = 0
        private set
    val finalPosition: Int
        get() = depth * horizontalPosition
    var horizontalPosition: Int = 0
        private set
    private var aim: Int = 0
    private val regex = Regex("^(.*) (\\d+)$")

    fun executeCommand(command: String) {
        val matchResult = regex.find(command.trim())
            ?: throw RuntimeException("Command [$command] is invalid!")
        val (action, rangeString) = matchResult.destructured
        val range = rangeString.toInt()
        when (action) {
            "forward" -> {
                depth += aim * range
                horizontalPosition += range
            }
            "down" -> aim += range
            "up" -> {
                if (aim - range < 0) throw RuntimeException("Cannot move above surface!")
                aim -= range
            }
            else -> throw RuntimeException("Action [$action] is invalid!")
        }
    }

    fun executeCommands(commands: String) {
        commands.trim().lines().forEach(::executeCommand)
    }

    override fun toString(): String {
        return "ImprovedPilot @$horizontalPosition:$depth; final position [$finalPosition]"
    }

}