package provider;

    public class GeradorProdutos {
        public static Produtos obterProdutoPadrao() {
            return new Produtos(
                    "Perfume Oil",
                    "Mega Discount, Impression of A...",
                    13,
                    8.4,
                    4.26,
                    65,
                    "Impression of Acqua Di Gio",
                    "fragrances",
                    "https://i.dummyjson.com/data/products/11/thumnail.jpg"
            );
        }

        public static Produtos obterProdutoDiferente() {
            return new Produtos(
                    "Perfume Oil 2",
                    "Mega Discount, Impression of A...",
                    13,
                    8.4,
                    4.26,
                    65,
                    "Impression of Acqua Di Gio",
                    "teste 123",
                    "https://i.dummyjson.com/data/products/11/thumnail.jpg"
            );
        }
    }
