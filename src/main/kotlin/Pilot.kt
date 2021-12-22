class Pilot {

    private val regex = Regex("^(.*) (\\d+)$")
    var depth: Int = 0
        private set
    var horizontalPosition: Int = 0
        private set
    val finalPosition: Int
        get() = depth * horizontalPosition

    fun executeCommand(command: String) {
        val matchResult = regex.find(command.trim())
            ?: throw RuntimeException("Command [$command] is invalid!")
        val (action, rangeString) = matchResult.destructured
        val range = rangeString.toInt()
        when (action) {
            "forward" -> horizontalPosition += range
            "down" -> depth += range
            "up" -> {
                if (depth - range < 0) throw RuntimeException("Cannot move above surface!")
                depth -= range
            }
            else -> throw RuntimeException("Action [$action] is invalid!")
        }
    }

    fun executeCommands(commands: String) {
        commands.trim().lines().forEach(::executeCommand)
    }

    override fun toString(): String {
        return "Pilot @$horizontalPosition:$depth; final position [$finalPosition]"
    }

}