case class Piece(x: Int, y: Int)

object NQueen extends App {
  type Board = List[Piece]

  val N = 8

  // 盤面に駒が入っているか
  def inBoard(b: Piece): Boolean = {
    (b.x >= 0 && b.x < N && b.y >= 0 && b.y < N)
  }

  // 縦横斜めに駒がいるか
  def check(b: Piece, bss: Board): Boolean = {
    // 縦横
    if (bss.exists(p => p.x == b.x) ||
        bss.exists(p => p.y == b.y)) {
      return false
    }
    for (i <- -1 * (N - 1) until (N - 1) by 1) {
      {
        // 右下
        val _b = Piece(b.x + i, b.y + i)
        if (inBoard(_b) && bss.exists(p => p == _b)) {
          return false
        }
      }
      {
        // 左下
        val _b = Piece(b.x + i, b.y - i)
        if (inBoard(_b) && bss.exists(p => p == _b)) {
          return false
        }
      }
    }
    true
  }

  def process(n: Int, bs: Board, as: List[Board]): List[Board] = {
    if (n >= N) {
      bs :: as
    } else {
      // 左(n)から置いていく
      var ass = as
      for (y <- 0 until N) {
        // 上(0)から置いていく
        bs match {
          case Nil => ass = process(n + 1, Piece(n, y) :: bs, as) ++ ass
          case b :: bss => {
            val _b = Piece(n, y)
            if ((b.y - 1 > y || y > b.y + 1) && check(_b, bs)) {
              ass = process(n + 1, _b :: bs, as) ++ ass
            }
          }
        }
      }
      ass
    }
  }

  val s = System.currentTimeMillis()
  val as = process(0, Nil, Nil)
  val e = System.currentTimeMillis()
  println(e - s, "msec", as.length)
}
