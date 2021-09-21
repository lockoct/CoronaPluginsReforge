import com.github.ajalt.clikt.core.subcommands
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

internal class ViewCommandExecutor : MagicCommandExecutor() {
    override fun getCommand(): MagicCommand {
        return ViewCommand().subcommands(SinglePageCommand(), MultiplePageCommand())
    }
}

internal class ViewCommand : MagicCommand() {
    override fun run() {
        sender?.sendMessage(getFormattedHelp())
    }
}

internal class SinglePageCommand : MagicCommand(name = "single") {
    override fun run() {
        val view = MagicView()
        view.layout = MagicLayout().apply {
            setComponent(0, 0, MagicComponent(ItemStack(Material.STONE).setName("test1")))
        }
    }
}

internal class MultiplePageCommand : MagicCommand(name = "multiple") {
    override fun run() {
        if (sender == null || sender !is Player) {
            throw Error("invalid sender")
        }

        val layout1 = MagicLayout()
        val layout2 = MagicLayout()

        layout1.setComponent(0, 0, MagicComponent(ItemStack(Material.GLASS)))
        layout2.setComponent(0, 0, MagicComponent(ItemStack(Material.STONE)))

        val view = MultiplePageView().apply {
            addLayout(layout1)
            addLayout(layout2)
        }

        view.open(sender as Player)
    }
}









