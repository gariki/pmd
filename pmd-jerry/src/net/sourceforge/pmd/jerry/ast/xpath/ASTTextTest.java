/* Generated By:JJTree: Do not edit this line. ASTTextTest.java */

package net.sourceforge.pmd.jerry.ast.xpath;

public class ASTTextTest extends SimpleNode {
  public ASTTextTest(int id) {
    super(id);
  }

  public ASTTextTest(XPath2Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XPath2ParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
