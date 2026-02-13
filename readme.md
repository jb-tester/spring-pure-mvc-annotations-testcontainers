# Non-boot Spring Web MVC project with JSP views; 
## Java-based configuration (no XML), beans are configured via scanned annotations

Without explicit Spring facet:
- OK: context is recognized;
- OK: Structure view is available and shows correct contents;
- OK: Spring view
- BUG: no gutters for repository methods
With facet:
- OK: if Spring facet is added manually, fileset is recognized
- BUG: after adding Spring facet, the beans are duplicated in Spring view
Spring Debugger:
- OK: tests running
- OK: remote debug (when tomcaut is run in docker)
- BUG: application - not available
   
 