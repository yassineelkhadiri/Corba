package calcul;


/**
* calcul/calcul_montantsPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./spec.idl
* Tuesday, 7 December 2021 18:07:18 o'clock WAT
*/

public abstract class calcul_montantsPOA extends org.omg.PortableServer.Servant
 implements calcul.calcul_montantsOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("calcul_ttc", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // calcul/calcul_montants/calcul_ttc
       {
         double mt_ht = in.read_double ();
         double taux = in.read_double ();
         double $result = (double)0;
         $result = this.calcul_ttc (mt_ht, taux);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:calcul/calcul_montants:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public calcul_montants _this() 
  {
    return calcul_montantsHelper.narrow(
    super._this_object());
  }

  public calcul_montants _this(org.omg.CORBA.ORB orb) 
  {
    return calcul_montantsHelper.narrow(
    super._this_object(orb));
  }


} // class calcul_montantsPOA