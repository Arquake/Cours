import { BookSummary } from "./BookSummary";

export interface Author {
    id: number,
    firstname: string,
    lastname: string,
    books: BookSummary[]
}