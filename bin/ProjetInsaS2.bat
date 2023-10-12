@echo off
set JLINK_VM_OPTIONS=
set DIR=%~dp0
"%DIR%\java" %JLINK_VM_OPTIONS% -m fr.insa.issenhuth.devis_batiment_fx/fr.insa.issenhuth.devis_batiment_fx.App %*
