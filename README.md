# Commander
Just a simple commanding system based on Spigot. (NOT USEFUL) Just provides a simple api for plugins to custom chat based commands.

**NOTICE**


This project requires SpigotASM to work.


**USAGES**

*In Game*

    #/commander [help | list] | #/ in order to get all registered commands

*Java*
*Example*


    @Command(
      String id /* Id of command*/, 
      String help /* The help page of the command, simulates the plugin.yml*/,
      boolean op /* Whether the command requires op to execute, default true */
    )
    class ExampleCommand implements CommandExecutor (Must be an available spigot command) {
      @Override
      /* ... */
    }
