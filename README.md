Demo of a "relay" - i.e. a client and server at the same time, written to use the Jacorb Orb (using IOR in file trick).

Dr Gary Allen.
University of Huddersfield

If you don't already have it, download Jacorb from the Jacorb web site and unpack it somewhere.

Also download the library jboss-rmi-api_1.0_spec-1.0.6.Final.jar from the Maven Repository at:

    https://mvnrepository.com/artifact/org.jboss.spec.javax.rmi/jboss-rmi-api_1.0_spec/1.0.6.Final.

This is a workaround to fix an issue with Java 11 and above.


To run the demo from IntelliJ:

1.  Add all the libraries from the Jacorb lib folder to the **module** (NOTE - add them to the module, not the project.  They are not all required, but I don't know the minmum set) **AND** add the jboss library too.

2.  Precompile the IDL.  To do this open a (IntelliJ) Terminal and type:


    cd RelayDemo/src
    <path_to_jacorb_dir>/bin/idl LMSServer.idl

e.g. I would type:

    cd RelayDemo/src
    /spare/jacorb-3.9/bin/idl LMSServer.idl

3.  You can then run the different components of the system.  You need to start them in the following order:


    HQServer
    LMSServer
    SensorClient



The client will produce output similar to the following:

    Calling relay...
    Result =
    Hello world !!


# DCSS_Assignment_2
